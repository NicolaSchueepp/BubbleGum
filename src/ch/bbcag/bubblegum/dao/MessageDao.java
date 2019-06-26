package ch.bbcag.bubblegum.dao;

import java.util.List;

import ch.bbcag.bubblegum.dao.querryoperation.ListReadOperation;
import ch.bbcag.bubblegum.dao.querryoperation.WriteOperation;
import ch.bbcag.bubblegum.dao.util.AbstractDao;
import ch.bbcag.bubblegum.model.Message;

public class MessageDao extends AbstractDao<Message> implements IMessageDao{

	
	@Override
	public List<Message> getByChatId(long chatId) {
		return executeCustomQuarry("SELECT m FROM Message m where m.chatId = :chatId", (q)->q.setParameter("chatId", chatId) , new ListReadOperation<Message>());
	}

	@Override
	public void deleteByChat(long chatId) {
		executeCustomQuarry("DELETE FROM Message m WHERE m.chatId = :chatId",
				(q) -> q.setParameter("chatId", chatId), new WriteOperation<Message>());
	}
	
	@Override
	public Class<Message> getClazz() {
		return Message.class;
	}
}
