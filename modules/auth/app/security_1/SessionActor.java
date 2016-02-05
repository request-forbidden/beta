package security_1;

import static akka.pattern.Patterns.ask;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import play.Logger;
import play.libs.Akka;
import play.libs.F.Callback;
import play.libs.F.Callback0;
import play.libs.Json;
import play.mvc.WebSocket;
import scala.concurrent.Await;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class SessionActor extends UntypedActor {

	private static ActorRef messenger = Akka.system().actorOf(Props.create(SessionActor.class));
	private static final String STATUS_SUCCESS = "OK";

	private static Map<Long, Map<Integer, WebSocket.Out<JsonNode>>> channels = new HashMap<Long, Map<Integer, WebSocket.Out<JsonNode>>>();

	public static void start(final Long userId, WebSocket.In<JsonNode> in, final WebSocket.Out<JsonNode> out) throws Exception {

		String result = (String) Await.result(ask(messenger, new Join(userId, out), 2000), Duration.create(1, SECONDS));

		if (STATUS_SUCCESS.equals(result)) {

			in.onMessage(new Callback<JsonNode>() {
				public void invoke(JsonNode event) {
					messenger.tell(event, messenger);
				}
			});

			in.onClose(new Callback0() {
				public void invoke() {
					SessionActor.removeChannel(userId, out);
					if(!SessionActor.isOnline(userId)){
						SessionActor.notifyUserStatus(userId);
					}
				}
			});

		} else {
			ObjectNode error = Json.newObject();
			error.put("error", result);
			out.write(error);
		}

	}

	@Override
	public void onReceive(Object message) throws Exception {

		if (message instanceof Join) {

			Join join = (Join) message;

			boolean wasOnline = SessionActor.isOnline(join.getUid());

			join.commit();

			if(!wasOnline){
				notifyUserStatus(join.uid);
			}

			getSender().tell(STATUS_SUCCESS, getSelf());

		}else{

			Logger.debug("[WebSocket] onRecive: "+message.toString());

		}

	}

    // ----------------------------------------

    public static boolean realTimeMsgUser(Long uid, JsonNode event){
        return realTimeMsgUser(uid, event, "notify");
    }

    public static boolean realTimeMsgUser(Long uid, JsonNode event, String type){

        Logger.debug(event.toString());

        if(channels.containsKey(uid)){

            ObjectNode n = Json.newObject();
            n.put("type", type);
            n.put("data", event);

            for( WebSocket.Out<JsonNode> chOut : channels.get(uid).values()){
                chOut.write(n);
            }

            return true;
        }

        return false;
    }

	private static void notifyUserStatus(Long uid){
		SessionActor.notifyUserStatus(uid, SessionActor.isOnline(uid));
	}

	private static void notifyUserStatus(Long uid, boolean online){

		ObjectNode n = Json.newObject();
		n.put("uid", uid);				// TODO some messenger information ! 
		n.put("type", "user_status"); 	//RemoteMessage.T_USER_STATUS
		n.put("online", online);

		notifyAll(n);
	}

	private static void notifyAll(JsonNode event) {

		int c = 0, u = 0;

		for(Map<Integer, WebSocket.Out<JsonNode>> userChannels : SessionActor.channels.values() ){
			u++;
			for(WebSocket.Out<JsonNode> chOut : userChannels.values()){
				chOut.write(event);
				c++;
			}
			Logger.debug("[WebSocket] notifyAll: "+c+" channels and "+u+" users notified: "+event.toString());
		}
	}

	private static void removeChannel(Long uid, WebSocket.Out<JsonNode> channel){
		if(SessionActor.channels.containsKey(uid)) {
			SessionActor.channels.get(uid).remove(channel.hashCode());
			if(SessionActor.channels.get(uid).size()<1){
				SessionActor.channels.remove(uid);
			}
		}
	}

	private static void appendChannel(Long uid, WebSocket.Out<JsonNode> channel){
		if(!SessionActor.channels.containsKey(uid)) {
			SessionActor.channels.put(uid, new HashMap<Integer, WebSocket.Out<JsonNode>>());
		}
		SessionActor.channels.get(uid).put(channel.hashCode(), channel);
	}

	public static boolean isOnline(Long uid){
		//check for size ! preformance improvment ?! 
		return SessionActor.channels.containsKey(uid); 
	}

	public static class Join {

		private Long uid;
		private WebSocket.Out<JsonNode> channel;

		public Join(Long uid, WebSocket.Out<JsonNode> channel) {
			this.channel = channel;
			this.uid = uid;
		}

		public Long getUid(){
			return this.uid;
		}

		public void commit(){
			SessionActor.appendChannel(uid, channel);
		}

	}

}
