package ch.bbcag.bubblegum.dao;

import java.util.List;

import ch.bbcag.bubblegum.model.UserInChat;

public interface IUserInChatDao {

	public UserInChat create(UserInChat userInChat);
	
	public List<UserInChat> getPersonalChats(Long userID);

	public List<UserInChat> searchPersonalChatByName(String name, Long userID);
	
//	public UserInChat getChatByPKs(UserInChatID userInChatID);
}
