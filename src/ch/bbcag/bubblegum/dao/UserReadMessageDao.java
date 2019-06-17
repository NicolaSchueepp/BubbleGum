package ch.bbcag.bubblegum.dao;

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
import ch.bbcag.bubblegum.model.UserReadMessage;

public class UserReadMessageDao implements IUserReadMessageDao{

	@Inject
	private QueryExecutor queryExecutor;
	
	@Override
	public UserReadMessage create(UserReadMessage userReadMessage) {
		queryExecutor.create(new QueryExecutionUnit<UserReadMessage>() {
			@Override
			public UserReadMessage execute(EntityManager entityManager, QueryExecutor queryExecutor) throws NoResultException, NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
				queryExecutor.prepareWrite();
				entityManager.persist(userReadMessage);
				queryExecutor.closeWrite();
				return userReadMessage;
			}
		});
		try {
			return queryExecutor.executeQuery();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public UserReadMessage get(long userId, long messageId) {
		queryExecutor.create(new QueryExecutionUnit<UserReadMessage>() {
			@Override
			public UserReadMessage execute(EntityManager entityManager, QueryExecutor queryExecutor) throws NoResultException, NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
				queryExecutor.prepareRead();
				TypedQuery<UserReadMessage> query = entityManager.createQuery("SELECT u FROM UserReadMessage u where u.userId = :userId AND u.messageId = :messageId",
						UserReadMessage.class);
				query.setParameter("userId", userId);
				query.setParameter("messageId", messageId);
				UserReadMessage userReadMessage = query.getSingleResult();
				queryExecutor.closeRead();
				return userReadMessage;
			}
		});
		try {
			return queryExecutor.executeQuery();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
