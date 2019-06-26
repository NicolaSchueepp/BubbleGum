package ch.bbcag.bubblegum.dao;

import ch.bbcag.bubblegum.model.ConversationAccessKey;

public interface IConversationAccessKeyDao {

	public void create(ConversationAccessKey conversationAccessKey);
	
	public void delete(ConversationAccessKey conversationAccessKey);
		
	public ConversationAccessKey getByHash(String hash);
	
	public ConversationAccessKey getByChatAnUser(long userId, long chatId);
}
