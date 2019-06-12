package ch.bbcag.bubblegum.dao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
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
import ch.bbcag.bubblegum.model.UserInChat;

@RequestScoped
public class UserInChatDao implements IUserInChatDao {

	@Inject
	private QueryExecutor queryExecutor;

	@Override
	public UserInChat create(UserInChat userInChat) {
		queryExecutor.create(new QueryExecutionUnit<UserInChat>() {
			@Override
			public UserInChat execute(EntityManager entityManager, QueryExecutor queryExecutor)
					throws NoResultException, NotSupportedException, SystemException, SecurityException,
					IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
				queryExecutor.prepareWrite();
				entityManager.persist(userInChat);
				queryExecutor.closeWrite();
				return userInChat;
			}
		});
		try {
			return queryExecutor.executeQuery();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<UserInChat> getPersonalChats(Long userID) {
		queryExecutor.create(new QueryExecutionUnit<List<UserInChat>>() {
			@Override
			public List<UserInChat> execute(EntityManager entityManager, QueryExecutor queryExecutor)
					throws NoResultException, NotSupportedException, SystemException, SecurityException,
					IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
				queryExecutor.prepareRead();
				TypedQuery<UserInChat> query = entityManager
						.createQuery("SELECT u FROM UserInChat u where u.user.id = :userID", UserInChat.class);
				query.setParameter("userID", userID);
				List<UserInChat> users = query.getResultList();
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
	public List<UserInChat> searchPersonalChatByName(String name, Long userID) {
		queryExecutor.create(new QueryExecutionUnit<List<UserInChat>>() {
			@Override
			public List<UserInChat> execute(EntityManager entityManager, QueryExecutor queryExecutor)
					throws NoResultException, NotSupportedException, SystemException, SecurityException,
					IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
				queryExecutor.prepareRead();
				TypedQuery<UserInChat> query = entityManager.createQuery(
						"SELECT c, u FROM UserInChat u join u.chat c where u.user.id = :userID and c.name like :name",
						UserInChat.class);
				query.setParameter("userID", userID);
				query.setParameter("name", "%" + name + "%");
				List<UserInChat> users = query.getResultList();
				if (users == null) {
					users = new ArrayList<UserInChat>();
				}
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

//	@Override
//	public UserInChat getChatByPKs(UserInChatID userInChatid) {
//		queryExecutor.create(new QueryExecutionUnit<UserInChat>() {
//			@Override
//			public UserInChat execute(EntityManager entityManager, QueryExecutor queryExecutor)
//					throws NoResultException, NotSupportedException, SystemException, SecurityException,
//					IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
//				queryExecutor.prepareRead();
//
//				UserInChat userInChat = entityManager.find(UserInChat.class, userInChatid);
//
//				queryExecutor.closeRead();
//				return userInChat;
//			}
//		});
//		try {
//			return queryExecutor.executeQuery();
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}

}
