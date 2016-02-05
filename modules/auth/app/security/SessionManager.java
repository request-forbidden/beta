package security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import org.apache.commons.codec.binary.Hex;

import play.db.jpa.JPA;
import play.libs.F;
import play.mvc.Http;
import play.mvc.Http.Request;
import play.mvc.Http.Response;
import play.mvc.Http.Session;
import models.User;

public class SessionManager {

	private static Map<Long, User> userList = new HashMap<>();
	private static Map<String, Long> hashUserMap = new HashMap<>();
	private static final String COOKIE_PASSHASH = "XCVIX";
	private static final Integer COOKIE_MAXAGE = 3600;

    public static Map<Long,User> getUserMap(){
        return userList;
    }

	public static void logout(Session session){

		Long uid = Long.valueOf(session.get("id"));

		if(SessionManager.userList.containsKey(uid)){

			if(SessionManager.userList.get(uid).getHash()!=null){
				SessionManager.hashUserMap.remove(SessionManager.userList.get(uid).getHash());
			}
			SessionManager.userList.remove(uid);

		}else{
			play.Logger.error("[SESSION] loging out with empty user ");
		}

        session.clear();
	}

	public static boolean userIsLoggedIn(Long user_id){
		return SessionManager.userList.containsKey(user_id);
	}

	public static boolean userIsOnline(Long user_id){
		return SessionActor.isOnline(user_id);
	}

    public static User getUserFromSessionList(Long uid){
        if (SessionManager.userList.containsKey(uid)){
            return SessionManager.userList.get(uid);
        }
        return null;
    }

	/**
	 * called by deadbolt 2 handler
	 * @return			
	 */
	public static User getUserForSession(Http.Context ctx) {

		Session session = ctx.session();
		Request request = ctx.request();
        Response response = ctx.response();

		Long uid = (session.get("id") != null) ? Long.valueOf(session.get("id")) : null;
		String hashcookie = null;

        if(uid!= null && !SessionManager.userList.containsKey(uid) && play.Play.isDev()){

            final Long tuid = uid;

            JPA.withTransaction(new F.Callback0() {
                @Override
                public void invoke() throws Throwable {
//                    User u = User.findById(tuid);
//                    SessionManager.userList.put(tuid, u);
                }
            });

            return SessionManager.userList.get(uid);
        }

		try{

            hashcookie = request.cookies().get(COOKIE_PASSHASH).value(); //null point exception ?

		}catch(NullPointerException e){ }

		if (uid == null && hashcookie!=null) { //no session but remember me ! 

			try{

				if (SessionManager.hashUserMap.containsKey(hashcookie)) { //session is gone but hashcookie is and information in memory

					uid = SessionManager.hashUserMap.get(hashcookie);

					if (SessionManager.userList.containsKey(uid)){

						String remoteAddress = request.remoteAddress();
						String userAgent = request.getHeader(Response.USER_AGENT);
						String hashcurrent = SessionManager.getMD5Hash(remoteAddress, userAgent, String.valueOf(uid));

						if (hashcookie.equals(hashcurrent)) { //yes this is this user ! he can login !! 

							session.put("id", String.valueOf(uid)); //restoring session

						}else{
							throw new BadHashCookieExceception("hash_not_equals");
						}

					}else{
						throw new BadHashCookieExceception("uid_not_found_on_list");
					}

				}else{
					throw new BadHashCookieExceception("hashcookie_not_found_on_list");
				}

			}catch(BadHashCookieExceception e){
				response.setCookie(COOKIE_PASSHASH, "", -COOKIE_MAXAGE);
				return null;
			}

		}

		if (SessionManager.userList.containsKey(uid)){ 
			return SessionManager.userList.get(uid);
        }

		return null;
	}

	// - private ---------------------------

	private static String getMD5Hash(String remoteAddress, String userAgent, String uid){

		MessageDigest md = null;

		try {

			md = MessageDigest.getInstance("MD5");
			md.update(remoteAddress.getBytes("UTF-8"));
			md.update(userAgent.getBytes("UTF-8"));
			md.update(uid.getBytes("UTF-8"));

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return new String(Hex.encodeHex(md.digest()));
	}
	
	// - LOGIN subclass	------------------------------------------------------

	public static class Login {

		private String login;
		private String password;
		public boolean rememberme = false;

		private User user = null;

		public String getLogin(){
			return this.login;
		}

		public void setLogin(String login){
			this.login=login;
		}

		public void setPassword(String password){
			this.password=password;
		}

		public String getPassword(){
			return this.password;
		}

		public void login(Session session, Request request, Response response){

			String string_id = String.valueOf(this.user.getId());

			session.put("id", string_id);

			String remoteAddress = request.remoteAddress();
			String userAgent = request.getHeader(Response.USER_AGENT);
			String hash = SessionManager.getMD5Hash(remoteAddress, userAgent, string_id);

			String sql = "UPDATE users SET last_login_date = ?, last_login_from = ?, last_login_useragent = ?, last_login_hash = ? WHERE id = ?";

			JPA.em().createNativeQuery(sql)
				.setParameter(1, new Date())
				.setParameter(2, remoteAddress)
				.setParameter(3, userAgent)
				.setParameter(4, hash)
				.setParameter(5, user.getId())
				.executeUpdate();

			if(this.rememberme){
				SessionManager.hashUserMap.put(hash, user.getId());
				response.setCookie(COOKIE_PASSHASH, hash, COOKIE_MAXAGE);
			}

			this.user.setRememberme(rememberme);
			this.user.setRemoteAddress(remoteAddress);
			this.user.setUserAgent(userAgent);
			this.user.setHash(hash);

			SessionManager.userList.put(this.user.getId(), this.user);

		}

		public String validate() {

//			this.user = User.getByAuthentication(login, password);

			if(this.user==null){
				return "Zły użytkownik lub hasło";
			}else if(!this.user.getActive()) {
				return "Użytkownik nie jest aktywny !";
			}else{
				return null;	
			}

		}

	}

	public static class BadHashCookieExceception extends Exception {

		private static final long serialVersionUID = 1L;
		
		public BadHashCookieExceception(String message) {
	        super(message);
	    }

	}

}