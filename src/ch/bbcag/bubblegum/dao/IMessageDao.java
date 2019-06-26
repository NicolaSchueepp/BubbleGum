package ch.bbcag.bubblegum.dao;

import java.util.List;

import ch.bbcag.bubblegum.model.Message;

public interface IMessageDao {

	public List<Message> getByChatId(long chatId);
	
	public void create(Message message);
	
	public void deleteByChat(long chatId);
	
}
