package ch.bbcag.bubblegum.dao;

import ch.bbcag.bubblegum.model.UserReadMessage;

public interface IUserReadMessageDao {

	public void create(UserReadMessage userReadMessage);
	
	public UserReadMessage getByUserIdAndMessageId(long userId, long messageId);
}
