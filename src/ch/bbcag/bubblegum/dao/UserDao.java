package ch.bbcag.bubblegum.dao;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import ch.bbcag.bubblegum.model.User;

public class UserDao implements IUserDao {

	@PersistenceContext
	private EntityManager em;

	@Resource
	private UserTransaction transaction;

	@Override
	public User create(User user) {
		try {
			transaction.begin();
			em.persist(user);
			transaction.commit();
		} catch (Exception e) {
			try {
				transaction.rollback();
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
		// TODO Auto-generated method stub
		return null;
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
