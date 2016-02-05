package secure;

import models.User;
import org.apache.commons.codec.binary.Hex;
import play.mvc.Http;
import play.mvc.Http.Request;
import play.mvc.Http.Response;
import play.mvc.Http.Session;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class SessionManager {

	private static Map<Long, User> userList = new HashMap<>();
	private static Map<String, Long> hashUserMap = new HashMap<>();
	private static final String COOKIE_PASSHASH = "XCVIX";
	private static final Integer COOKIE_MAXAGE = 3600;

    public static Map<Long,User> getUserMap(){
        return userList;
    }

	public static void logout(play.mvc.Http.Session session){

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

		public void login(play.mvc.Http.Session session, play.mvc.Http.Request request, play.mvc.Http.Response response){

			String string_id = String.valueOf(this.user.getId());

			session.put("id", string_id);

			String remoteAddress = request.remoteAddress();
			String userAgent = request.getHeader(play.mvc.Http.Response.USER_AGENT);
//			String hash = SessionManager.getMD5Hash(remoteAddress, userAgent, string_id);

//			SessionManager.userList.put(this.user.getId(), this.user);

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