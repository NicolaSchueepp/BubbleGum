package ch.bbcag.bubblegum.service.message;

import javax.websocket.Session;

import ch.bbcag.bubblegum.model.ConversationAccessKey;

public class Client {

	private String hash;
	private Session session;
	private ConversationAccessKey accessKey;
	
	public Client(Session session,String hash, ConversationAccessKey accessKey) {
		// TODO Auto-generated constructor stub
	}
	
	public ConversationAccessKey getAccessKey() {
		return accessKey;
	}
	
	public String getHash() {
		return hash;
	}
	
	public void send(JsonResponseMessage message) {
		session.getAsyncRemote().sendText(message.toJson());
	}
	
}

