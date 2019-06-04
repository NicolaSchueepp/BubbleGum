package ch.bbcag.bubblegum.service;

import javax.websocket.Session;

import ch.bbcag.bubblegum.service.message.JsonRequestMessage;

public interface IMessageService {

	public void registerClinet(Session session);
	
	public void removeClient(Session session);
	
	public void spreadMessage(JsonRequestMessage message);
}
