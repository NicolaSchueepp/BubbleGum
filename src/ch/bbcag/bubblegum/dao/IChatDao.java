package ch.bbcag.bubblegum.dao;

import java.util.List;

import ch.bbcag.bubblegum.model.Chat;

public interface IChatDao {
	
	public List<Chat> getAllChats();

	public List<Chat> searchChatByName(String name);

	public Chat getQuickChatByMembers(long userId1, long userId2);
	
	public Chat create(Chat chat);
}
