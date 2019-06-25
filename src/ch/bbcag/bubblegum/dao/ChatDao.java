package ch.bbcag.bubblegum.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import ch.bbcag.bubblegum.dao.querryoperation.ListReadOperation;
import ch.bbcag.bubblegum.dao.querryoperation.SingleReadOperation;
import ch.bbcag.bubblegum.dao.util.AbstractDao;
import ch.bbcag.bubblegum.dao.util.ExecutionUnit;
import ch.bbcag.bubblegum.model.Chat;

public class ChatDao extends AbstractDao<Chat> implements IChatDao {

	@PersistenceUnit
	private EntityManagerFactory emf;

	@Override
	public List<Chat> searchChatByName(String name) {
		return executeCustomQuarry("SELECT c FROM Chat c where c.name like :name",(q)-> q.setParameter("name", "%"+name+"%"), new ListReadOperation<Chat>());
	}

	@Override
	public Chat getQuickChatByMembers(long userId1, long userId2) {
		return executeCustomQuarry("SELECT c1 FROM UserInChat u1, UserInChat u2 "
							+ "INNER JOIN u1.chat c1 "
							+ "INNER JOIN u2.chat c2 "
							+ "WHERE u1.user.id = :userId1 AND c1.isBubble = false AND u2.user.id = :userId2 AND c1 = c2",
							(q) -> q.setParameter("userId1", userId1).setParameter("userId2", userId2), new SingleReadOperation<Chat>());
	}

	@Override
	public Class<Chat> getClazz() {
		return Chat.class;
	}
	
	
}
