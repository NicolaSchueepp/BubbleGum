package ch.bbcag.bubblegum.dao;

import java.util.List;

import ch.bbcag.bubblegum.dao.querryoperation.ListReadOperation;
import ch.bbcag.bubblegum.dao.querryoperation.SingleReadOperation;
import ch.bbcag.bubblegum.dao.util.AbstractDao;
import ch.bbcag.bubblegum.model.UserInChat;

public class UserInChatDao extends AbstractDao<UserInChat> implements IUserInChatDao{


	@Override
	public List<UserInChat> getByUserId(Long userId) {
		return executeCustomQuarry("SELECT u FROM UserInChat u where u.user.id = :userId", (q) -> q.setParameter("userId", userId) , new ListReadOperation<UserInChat>());
	}

	@Override
	public List<UserInChat> getByChatId(long chatId) {
		return executeCustomQuarry("SELECT u FROM UserInChat u where u.chat.id = :chatId", (q) -> q.setParameter("chatId", chatId) , new ListReadOperation<UserInChat>());
	}

	@Override
	public UserInChat getByUserIdAndChatId(long userId, long chatId) {
		return executeCustomQuarry("SELECT u FROM UserInChat u where u.user.id = :userId AND u.chat.id = :chatId",
				(q) -> q.setParameter("userId", userId).setParameter("chatId", chatId) , new SingleReadOperation<UserInChat>());
	}
	
	@Override
	public Class<UserInChat> getClazz() {
		return UserInChat.class;
	}






}
