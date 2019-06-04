package ch.bbcag.bubblegum.websocket;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.websocket.Session;

import ch.bbcag.bubblegum.model.ConversationAccessKey;
import ch.bbcag.bubblegum.service.IConversationAccessService;

public class ClientPool {

	private Set<Client> clients;	
	
	@Inject
	private IConversationAccessService conversationAccessService;
	
	public ClientPool() {
		clients = Collections.synchronizedSet(new HashSet<Client>());
	}
	
	public void add(Session session) {
    	String key = session.getRequestParameterMap().get("key").get(0);
    	ConversationAccessKey accessKey = conversationAccessService.getAccessKey(hash);
    	Client client = new Client(session, accessKey);
		clients.add(client);
	}
	
	public Client get(String key) {
		for (Client client : clients) {
			if(client.getKey().equals(key)) {
				return client;
			}
		}
		return null;
	}

	public void remove(Session session) {
		
	}
	
	public void reciveMessage() {
		
	}
	
}
