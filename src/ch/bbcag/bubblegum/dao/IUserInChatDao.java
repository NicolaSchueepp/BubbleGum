package ch.bbcag.bubblegum.dao;

import java.util.List;

import ch.bbcag.bubblegum.model.Chat;
import ch.bbcag.bubblegum.model.User;
import ch.bbcag.bubblegum.model.UserInChat;

public interface IUserInChatDao {

	public UserInChat create(UserInChat userInChat);
	
	public List<UserInChat> getPersonalChats(Long userID);

	public List<UserInChat> searchPersonalChatByName(String name, Long userID);
	
	public UserInChat getByUserIdAndChatId(long userId, long chatId);
	
	public List<User> getMembersByChat(long chatId);
}
