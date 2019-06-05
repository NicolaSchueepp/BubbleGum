package ch.bbcag.bubblegum.dao;

import java.util.List;

import ch.bbcag.bubblegum.model.Chat;

public interface IChatDao {
	
	public List<Chat> getAllChats();

	public List<Chat> searchChatByName(String name);
	
}
