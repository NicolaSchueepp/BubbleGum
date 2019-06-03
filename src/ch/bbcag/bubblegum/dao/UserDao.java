package ch.bbcag.bubblegum.dao;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import ch.bbcag.bubblegum.model.User;

public class UserDao implements IUserDao {

	@PersistenceUnit
	private EntityManagerFactory emf;

	@Resource
	private UserTransaction transaction;

	@Override
	public User create(User user) {
		EntityManager em = emf.createEntityManager();
		try {
			transaction.begin();
			em.joinTransaction();
			em.persist(user);
			em.flush();
			transaction.commit();
		} catch (Exception e) {
			try {
				if (transaction.getStatus() == Status.STATUS_ACTIVE) {
					transaction.rollback();
				}
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				throw new RuntimeException(e1);
			}
			throw new RuntimeException(e);
		} finally {
			em.close();
		}
		return user;
	}

	@Override
	public User getUserByEmail(String email) {
		EntityManager em = emf.createEntityManager();
		User user;
		try {
//			transaction.begin();
//			em.joinTransaction();
			TypedQuery<User> query = em.createQuery("SELECT u FROM User u where u.email = :email", User.class);
			query.setParameter("email", email);
//			em.flush();
//			transaction.commit();
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
