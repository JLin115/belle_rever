package webSocket;


 
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class EchoServer
 */

@ServerEndpoint("/EchoServer")
public class EchoServer{
    static Map<String, RemoteEndpoint.Basic> allRemote = new HashMap<String, RemoteEndpoint.Basic>();
    
    @OnOpen
    public void onOpen(Session session){
    	System.out.println(session.getId() + " has opened a connection");
    	try {
    	RemoteEndpoint.Basic basicRemote = session.getBasicRemote();
    	allRemote.put(session.getId(), basicRemote);
			basicRemote.sendText("Connection Established");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @OnMessage
    public void onMessage(String message , Session session){
    	System.out.println("Message from " +session.getId()+":"+message);

    	try {
    		session.getBasicRemote().sendText(message);
    		
    		RemoteEndpoint.Basic basic = allRemote.get(0);
    		basic.sendText("123..."+message);
    		
    		
//    		for(String id  : allRemote.keySet()){
//    			RemoteEndpoint.Basic basic = allRemote.get(id);
//    			basic.sendText("123..."+message);
//   
//    			
//    		}
    		System.out.println("allRemote.." +  allRemote.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
    	
    	
    }
    @OnClose
    public void onClose(Session session){
    	allRemote.remove(session.getId());
    	System.err.println("session"+session.getId()+" has ended");
    }


  

}
