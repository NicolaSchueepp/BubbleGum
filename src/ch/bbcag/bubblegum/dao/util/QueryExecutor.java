package ch.bbcag.bubblegum.dao.util;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

public class QueryExecutor {

	@PersistenceUnit
	private EntityManagerFactory emf;

	@Resource
	private UserTransaction transaction;

	private QueryExecutionUnit<?> executionUnit;

	private EntityManager entityManager;

	public void create(QueryExecutionUnit<?> executionUnit) {
		this.entityManager = emf.createEntityManager();
		this.executionUnit = executionUnit;
	}

	public void prepareWrite() throws NotSupportedException, SystemException {
		transaction.begin();
		entityManager.joinTransaction();

	}

	public void prepareRead() {
	}

	public void closeRead() {
		entityManager.clear();
		emf.getCache().evictAll();
	}

	public void closeWrite() throws SecurityException, IllegalStateException, RollbackException,
			HeuristicMixedException, HeuristicRollbackException, SystemException {
		entityManager.clear();
		entityManager.flush();
		transaction.commit();
	}

	@SuppressWarnings("unchecked")
	public <T> T executeQuery() throws DaoException {
		T result;
		try {
			result = (T) executionUnit.execute(entityManager, this);
			if (transaction.getStatus() == Status.STATUS_ACTIVE) {
				transaction.rollback();
			}
			entityManager.close();
		} catch (NoResultException e) {
			result = null;
		} catch (IllegalStateException | SecurityException | SystemException | NotSupportedException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			throw new DaoException(e);
		}
		return result;
	}

}
