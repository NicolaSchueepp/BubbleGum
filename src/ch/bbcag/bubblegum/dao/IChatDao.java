package ch.bbcag.bubblegum.dao;

import java.util.List;

import ch.bbcag.bubblegum.model.Chat;

public interface IChatDao {
	
	public List<Chat> getAll();

	public List<Chat> searchChatByName(String name);

	public Chat getQuickChatByMembers(long userId1, long userId2);
	
	public void create(Chat chat);
	
	public Chat getById(long id);

}
