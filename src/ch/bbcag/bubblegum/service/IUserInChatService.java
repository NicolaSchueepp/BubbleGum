package ch.bbcag.bubblegum.service;

import java.util.List;

import ch.bbcag.bubblegum.model.UserInChat;

public interface IUserInChatService {
	
	public List<UserInChat> getAllChats();

	public List<UserInChat> getUsers(Long chatId);
	
	public boolean addUser(long chatId, long userId, boolean admin);

	public boolean removeUser(Long valueOf, Long userId);

	public boolean removeSelf(Long chatId);

	public boolean promote(Long chatId, Long userId);
}
