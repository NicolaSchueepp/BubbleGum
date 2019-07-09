package ch.bbcag.bubblegum.dao;

import ch.bbcag.bubblegum.dao.querryoperation.SingleReadOperation;
import ch.bbcag.bubblegum.dao.util.AbstractDao;
import ch.bbcag.bubblegum.model.ConversationAccessKey;

public class ConversationAccessKeyDao extends AbstractDao<ConversationAccessKey> implements IConversationAccessKeyDao{

	@Override
	public ConversationAccessKey getByChatAnUser(long userId, long chatId) {
		return executeCustomQuarry("SELECT c FROM ConversationAccessKey c where c.userId = :userId and c.chatId = :chatId", 
				(q) -> q.setParameter("userId", userId).setParameter("chatId", chatId), new SingleReadOperation<ConversationAccessKey>());
	}

	@Override
	public ConversationAccessKey getByHash(String hash) {
		return executeCustomQuarry("SELECT c FROM ConversationAccessKey c where c.hash = :hash",
				(q) -> q.setParameter("hash", hash), new SingleReadOperation<ConversationAccessKey>());
	}
	
	@Override
	public Class<ConversationAccessKey> getClazz() {
		return ConversationAccessKey.class;
	}

	
}
