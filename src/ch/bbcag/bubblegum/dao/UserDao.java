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
import ch.bbcag.bubblegum.model.User;

public class UserDao implements IUserDao {

	@PersistenceUnit
	private EntityManagerFactory emf;

	@Resource
	private UserTransaction transaction;

	@Inject
	private QueryExecutor queryExecutor;

	@Override
	public User create(User user) {
		queryExecutor.create(new QueryExecutionUnit<Void>() {
			@Override
			public Void execute(EntityManager entityManager, QueryExecutor queryExecutor)
					throws NoResultException, NotSupportedException, SystemException, SecurityException,
					IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
				queryExecutor.prepareWrite();
				entityManager.persist(user);
				queryExecutor.closeWrite();
				return null;
			}
		});
		try {
			return queryExecutor.executeQuery();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public User getUserByEmail(String email) {
		queryExecutor.create(new QueryExecutionUnit<User>() {
			@Override
			public User execute(EntityManager entityManager, QueryExecutor queryExecutor)
					throws NoResultException, NotSupportedException, SystemException, SecurityException,
					IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
				queryExecutor.prepareRead();
				TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u where u.email = :email",
						User.class);
				query.setParameter("email", email);

				User user = query.getSingleResult();
				queryExecutor.closeRead();
				return user;
			}
		});
		try {
			return queryExecutor.executeQuery();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public User getUserById(Long id) {
		queryExecutor.create(new QueryExecutionUnit<User>() {
			@Override
			public User execute(EntityManager entityManager, QueryExecutor queryExecutor)
					throws NoResultException, NotSupportedException, SystemException, SecurityException,
					IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
				queryExecutor.prepareRead();
				TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u where u.id = :id", User.class);
				query.setParameter("id", id);

				User user = query.getSingleResult();
				queryExecutor.closeRead();
				return user;
			}
		});
		try {
			return queryExecutor.executeQuery();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<User> searchUserByName(String name) {
		queryExecutor.create(new QueryExecutionUnit<List<User>>() {
			@Override
			public List<User> execute(EntityManager entityManager, QueryExecutor queryExecutor)
					throws NoResultException, NotSupportedException, SystemException, SecurityException,
					IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
				queryExecutor.prepareRead();
				TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u where u.name like %:name%",
						User.class);
				query.setParameter("name", name);

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

}
