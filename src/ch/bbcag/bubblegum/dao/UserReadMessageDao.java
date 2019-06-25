package ch.bbcag.bubblegum.dao;

import ch.bbcag.bubblegum.dao.querryoperation.SingleReadOperation;
import ch.bbcag.bubblegum.dao.util.AbstractDao;
import ch.bbcag.bubblegum.model.UserReadMessage;

public class UserReadMessageDao extends AbstractDao<UserReadMessage> implements IUserReadMessageDao{

	
	@Override
	public UserReadMessage getByUserIdAndMessageId(long userId, long messageId) {
		return executeCustomQuarry("SELECT u FROM UserReadMessage u where u.userId = :userId AND u.messageId = :messageId", 
				(q) -> q.setParameter("userId", userId).setParameter("messageId", messageId), new SingleReadOperation<UserReadMessage>());
	}

	@Override
	public Class<UserReadMessage> getClazz() {
		return UserReadMessage.class;
	}

}
