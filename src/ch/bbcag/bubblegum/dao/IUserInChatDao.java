package ch.bbcag.bubblegum.dao;

import java.util.List;

import ch.bbcag.bubblegum.model.UserInChat;

public interface IUserInChatDao {

	public void create(UserInChat userInChat);
	
	public void update(UserInChat userInChat);
	
	public void delete(UserInChat userInChat);
	
	public List<UserInChat> getByUserId(Long userID);
	
	public UserInChat getByUserIdAndChatId(long userId, long chatId);
	
	public List<UserInChat> getByChatId(long chatId);

	
}
