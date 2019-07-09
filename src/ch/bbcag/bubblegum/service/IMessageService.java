package ch.bbcag.bubblegum.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.websocket.Session;

import ch.bbcag.bubblegum.model.Message;
import ch.bbcag.bubblegum.service.message.JsonRequestMessage;

public interface IMessageService {

	public void registerClinet(Session session);
	
	public void removeClient(Session session);
	
	public void spreadMessage(JsonRequestMessage message);
	
	public List<Message> getMessages(long chatId, String hash);
	
	public ArrayList<Entry<Message, Integer>> getNewQuickMessages();
	
	public ArrayList<Entry<Message, Integer>> getNewBubbleMessages();
	
	public void deleteMessages(long chatId);
}
