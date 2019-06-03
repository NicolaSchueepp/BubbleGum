package ch.bbcag.bubblegum.dao;

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
import javax.transaction.Status;
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
	private QueryExecutor quarryExecutor;

	@Override
	public User create(User user) {
		quarryExecutor.create(new QueryExecutionUnit<Void>() {
			@Override
			public Void execute(EntityManager entityManager, QueryExecutor quarryExecutor)
					throws NoResultException, NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
				quarryExecutor.prepareWrite();
				entityManager.persist(user);
				quarryExecutor.closeWrite();
				return null;
			}
		});
		try {
			return quarryExecutor.executeQuery();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public User getUserByEmail(String email) {
		EntityManager em = emf.createEntityManager();
		User user;
		try {
			TypedQuery<User> query = em.createQuery("SELECT u FROM User u where u.email = :email", User.class);
			query.setParameter("email", email);
			user = query.getSingleResult();
		} catch (NoResultException e1) {
			user = null;
		} catch (Exception e2) {
			try {
				if (transaction.getStatus() == Status.STATUS_ACTIVE) {
					transaction.rollback();
				}
			} catch (IllegalStateException | SecurityException | SystemException e3) {
				throw new RuntimeException(e3);
			}
			throw new RuntimeException(e2);
		} finally {
			em.close();
		}
		return user;
	}

	@Override
	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User searchUserByName() {
		// TODO Auto-generated method stub
		return null;
	}

}
