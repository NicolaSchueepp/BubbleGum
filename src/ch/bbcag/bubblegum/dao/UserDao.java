package ch.bbcag.bubblegum.dao;

import java.util.List;

import ch.bbcag.bubblegum.dao.querryoperation.ListReadOperation;
import ch.bbcag.bubblegum.dao.querryoperation.SingleReadOperation;
import ch.bbcag.bubblegum.dao.util.AbstractDao;
import ch.bbcag.bubblegum.model.User;

public class UserDao extends AbstractDao<User> implements IUserDao {

	@Override
	public User getUserByEmail(String email) {
		return executeCustomQuarry("SELECT u FROM User u where u.email = :email", (q) -> q.setParameter("email", email) , new SingleReadOperation<User>());
	}

	@Override
	public List<User> searchUserByName(String name) {
		return executeCustomQuarry("SELECT u FROM User u where u.name like :name", (q) -> q.setParameter("name", "%"+ name +"%"), new ListReadOperation<User>());
	
	}

	@Override
	public Class<User> getClazz() {
		return User.class;
	}

	@Override
	public User updateUser(User user) {
		queryExecutor.create(new QueryExecutionUnit<User>() {
			@Override
			public User execute(EntityManager entityManager, QueryExecutor queryExecutor)
					throws NoResultException, NotSupportedException, SystemException, SecurityException,
					IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
				queryExecutor.prepareWrite();
				entityManager.merge(user);
				queryExecutor.closeWrite();
				return user;
			}
		});
		try {
			return queryExecutor.executeQuery();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
