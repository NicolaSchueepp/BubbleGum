package ch.bbcag.bubblegum.service.message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Singleton;


@Singleton
public class ClientPool {

	private Set<Client> clients;	
	
	public ClientPool() {
		clients = Collections.synchronizedSet(new HashSet<Client>());
	}
	
	public void add(Client client) {
		clients.add(client);
	}
	
	public void remove(Client client) {
		clients.remove(client);
	}
	
	public Client getByHash(String hash) {
		for (Client client : clients) {
			if(client.getHash().equals(hash))
				return client;
		}
		return null;
	}
	
	public List<Client> getByChatId(long chatId){
		List<Client> clients = new ArrayList<Client>();
		for (Client client : this.clients) {
			if(client.getAccessKey().getChatId() == chatId)
				clients.add(client);
		}
		return clients;
	}
	
}
