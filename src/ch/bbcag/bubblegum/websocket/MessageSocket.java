package ch.bbcag.bubblegum.websocket;

import java.io.StringReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import ch.bbcag.bubblegum.model.ConversationAccessKey;
import ch.bbcag.bubblegum.service.IConversationAccessService;

@ServerEndpoint("/chatService")
public class MessageSocket {

	@Inject
	private ClientPool clientPool;
	
	
    @OnMessage
    public void onMessage(String message, Session session) {
    	clientPool.get
    	
    	if(clie)
    	
    	
    	JsonReader reader = Json.createReader(new StringReader(message));
    	JsonObject jsonObject = reader.readObject();
    	for (Session peer : peers) {
    		peer.getAsyncRemote().sendText(jsonObject.getString("key") + " : " + jsonObject.getString("text"));
    	}
    }

    @OnOpen
    public void onOpen(Session session) {
    	clientPool.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        clientPool.remove(session);
    }

 
	  
}
