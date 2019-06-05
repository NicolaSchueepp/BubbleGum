package ch.bbcag.bubblegum.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import ch.bbcag.bubblegum.dao.util.QueryExecutionUnit;
import ch.bbcag.bubblegum.dao.util.QueryExecutor;
import ch.bbcag.bubblegum.model.Message;
import ch.bbcag.bubblegum.model.User;

public class MessageDao implements IMessageDao{

	@Inject
	private QueryExecutor queryExecutor;
	
	@Override
	public List<Message> getByChatId(long chatId) {
		queryExecutor.create(new QueryExecutionUnit<List<User>>() {
			@Override
			public List<User> execute(EntityManager entityManager, QueryExecutor queryExecutor) throws NoResultException, NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
				queryExecutor.prepareRead();
				TypedQuery<User> query = entityManager.createQuery("SELECT m FROM Message m where m.chatId = :chatId",
						User.class);
				query.setParameter("chatId", chatId);

				List<User> message = query.getResultList();
				queryExecutor.closeRead();
				return message;
			}
		});
		try {
			return queryExecutor.executeQuery();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Message create(Message message) {
		queryExecutor.create(new QueryExecutionUnit<Message>() {
			@Override
			public Message execute(EntityManager entityManager, QueryExecutor queryExecutor) throws NoResultException, NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
				queryExecutor.prepareWrite();
				entityManager.persist(message);
				queryExecutor.closeWrite();
				return message;
			}
		});
		try {
			return queryExecutor.executeQuery();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	
}
