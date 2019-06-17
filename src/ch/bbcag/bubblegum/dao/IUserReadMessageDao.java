package ch.bbcag.bubblegum.dao;

import ch.bbcag.bubblegum.model.UserReadMessage;

public interface IUserReadMessageDao {

	public UserReadMessage create(UserReadMessage userReadMessage);
	
	public UserReadMessage get(long userId, long messageId);
}
