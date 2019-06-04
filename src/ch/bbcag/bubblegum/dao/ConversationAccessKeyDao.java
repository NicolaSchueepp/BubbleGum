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
import ch.bbcag.bubblegum.model.ConversationAccessKey;
import ch.bbcag.bubblegum.model.User;

public class ConversationAccessKeyDao implements IConversationAccessKeyDao{

	@Inject
	QueryExecutor queryExecutor;
	
	@Override
	public ConversationAccessKey create(ConversationAccessKey conversationAccessKey) {
		queryExecutor.create(new QueryExecutionUnit<ConversationAccessKey>() {
			@Override
			public ConversationAccessKey execute(EntityManager entityManager, QueryExecutor queryExecutor) throws NoResultException, NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
				queryExecutor.prepareWrite();
				entityManager.persist(conversationAccessKey);
				queryExecutor.closeWrite();
				return conversationAccessKey;
			}
		});
		try {
			return queryExecutor.executeQuery();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	@Override
	public ConversationAccessKey getByChatAnUser(long userId, long chatId) {
		queryExecutor.create(new QueryExecutionUnit<ConversationAccessKey>() {
			@Override
			public ConversationAccessKey execute(EntityManager entityManager, QueryExecutor queryExecutor) throws NoResultException, NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
				queryExecutor.prepareRead();
				TypedQuery<ConversationAccessKey> query = entityManager.createQuery("SELECT c FROM ConversationAccessKey c where c.userId = :userId and c.chatId = :chatId",
						ConversationAccessKey.class); 
				query.setParameter("userId", userId);
				query.setParameter("chatId", chatId);
				ConversationAccessKey accessKey = query.getSingleResult();
				queryExecutor.closeRead();
				return accessKey;
			}
		});
		try {
			return queryExecutor.executeQuery();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(ConversationAccessKey conversationAccessKey) {
		queryExecutor.create(new QueryExecutionUnit<Void>() {
			@Override
			public Void execute(EntityManager entityManager, QueryExecutor queryExecutor) throws NoResultException, NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
				queryExecutor.prepareWrite();
				TypedQuery<ConversationAccessKey> query = entityManager.createQuery("DELETE FROM ConversationAccessKey c WHERE c.id = :id",
						ConversationAccessKey.class);
				query.setParameter("id", conversationAccessKey.getId());
				query.executeUpdate();
				queryExecutor.closeWrite();
				return null;
			}
		});
		try {
			queryExecutor.executeQuery();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public ConversationAccessKey getByHash(String hash) {
		queryExecutor.create(new QueryExecutionUnit<ConversationAccessKey>() {
			@Override
			public ConversationAccessKey execute(EntityManager entityManager, QueryExecutor queryExecutor) throws NoResultException, NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
				queryExecutor.prepareRead();
				TypedQuery<ConversationAccessKey> query = entityManager.createQuery("SELECT c FROM ConversationAccessKey c where c.hash = :hash",
						ConversationAccessKey.class);
				query.setParameter("hash", hash);

				ConversationAccessKey accessKey = query.getSingleResult();
				queryExecutor.closeRead();
				return accessKey;
			}
		});
		try {
			return queryExecutor.executeQuery();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}



}
