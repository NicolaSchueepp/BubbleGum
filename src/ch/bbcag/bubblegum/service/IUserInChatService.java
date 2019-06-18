package ch.bbcag.bubblegum.service;

import java.util.List;

import ch.bbcag.bubblegum.model.UserInChat;

public interface IUserInChatService {
	
	public List<UserInChat> getAllChats();

	public List<UserInChat> getUsers(Long chatId);
}
