package ch.bbcag.bubblegum.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import ch.bbcag.bubblegum.dao.util.QueryExecutionUnit;
import ch.bbcag.bubblegum.dao.util.QueryExecutor;
import ch.bbcag.bubblegum.model.Chat;
import ch.bbcag.bubblegum.model.User;

public class ChatDao implements IChatDao {

	@PersistenceUnit
	private EntityManagerFactory emf;

	@Resource
	private UserTransaction transaction;

	@Inject
	private QueryExecutor queryExecutor;

	@Override
	public List<Chat> getAllChats() {
		queryExecutor.create(new QueryExecutionUnit<List<User>>() {
			@Override
			public List<User> execute(EntityManager entityManager, QueryExecutor queryExecutor)
					throws NoResultException, NotSupportedException, SystemException, SecurityException,
					IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
				queryExecutor.prepareRead();
				TypedQuery<User> query = entityManager.createQuery("SELECT c FROM Chat c", User.class);

				List<User> users = query.getResultList();
				queryExecutor.closeRead();
				return users;
			}
		});
		try {
			return queryExecutor.executeQuery();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Chat> searchChatByName(String name) {
		queryExecutor.create(new QueryExecutionUnit<List<Chat>>() {
			@Override
			public List<Chat> execute(EntityManager entityManager, QueryExecutor queryExecutor)
					throws NoResultException, NotSupportedException, SystemException, SecurityException,
					IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
				queryExecutor.prepareRead();
				TypedQuery<Chat> query = entityManager.createQuery("SELECT c FROM Chat c where c.name like :name",
						Chat.class);
				query.setParameter("name", "%" + name + "%");

				List<Chat> chats = query.getResultList();
				queryExecutor.closeRead();
				return chats;
			}
		});
		try {
			return queryExecutor.executeQuery();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Chat getQuickChatByMembers(long userId1, long userId2) {
		queryExecutor.create(new QueryExecutionUnit<Chat>() {
			@Override
			public Chat execute(EntityManager entityManager, QueryExecutor queryExecutor)
					throws NoResultException, NotSupportedException, SystemException, SecurityException,
					IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
				queryExecutor.prepareRead();
				TypedQuery<Chat> query = entityManager.createQuery("SELECT c1 FROM UserInChat u1, UserInChat u2 "
																+ "INNER JOIN u1.chat c1 "
																+ "INNER JOIN u2.chat c2 "
																+ "WHERE u1.user = :user1 AND c1.isBubble = false AND u2.user = :user2 AND c1 = c2",
																Chat.class);
				User user1 = new User();
				user1.setId(userId1);
				query.setParameter("user1", user1);
				User user2 = new User();
				user2.setId(userId2);
				query.setParameter("user2",  user2);
				Chat chat = query.getSingleResult();
				queryExecutor.closeRead();
				return chat;
			}
		});
		try {
			return queryExecutor.executeQuery();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Chat create(Chat chat) {
		queryExecutor.create(new QueryExecutionUnit<Chat>() {
			@Override
			public Chat execute(EntityManager entityManager, QueryExecutor queryExecutor) throws NoResultException, NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
				queryExecutor.prepareWrite();
				entityManager.persist(chat);
				queryExecutor.closeWrite();
				return chat;
			}
		});
		try {
			return queryExecutor.executeQuery();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Chat getById(long id) {
		queryExecutor.create(new QueryExecutionUnit<Chat>() {
			@Override
			public Chat execute(EntityManager entityManager, QueryExecutor queryExecutor)
					throws NoResultException, NotSupportedException, SystemException, SecurityException,
					IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
				queryExecutor.prepareRead();
				TypedQuery<Chat> query = entityManager.createQuery("SELECT c FROM Chat c where c.id = :id",
						Chat.class);
				query.setParameter("id", id);

				Chat chat = query.getSingleResult();
				queryExecutor.closeRead();
				return chat;
			}
		});
		try {
			return queryExecutor.executeQuery();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
}
