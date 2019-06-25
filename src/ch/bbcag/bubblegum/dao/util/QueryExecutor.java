package ch.bbcag.bubblegum.dao.util;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import ch.bbcag.bubblegum.dao.querryoperation.QuerryOperation;

public class QueryExecutor<T> {
	
		
	@PersistenceUnit
	private EntityManagerFactory emf;

	@Resource
	private UserTransaction transaction;

	private EntityManager entityManager;


	public <E> E executeQuery(ExecutionUnit<EntityManager, E> executionUnit, QuerryOperation<E, T> querryOperation) throws DaoException {
		this.entityManager = emf.createEntityManager();
		E result;
		try {
			querryOperation.prepare(this);
			result = executionUnit.execute(entityManager);
			querryOperation.close(this);
			if (transaction.getStatus() == Status.STATUS_ACTIVE) {
				transaction.rollback();
			}
		} catch (NoResultException e) {
			result = null;
		} catch (IllegalStateException | SecurityException | SystemException e) {
			throw new DaoException(e);
		}
		return result;
	}
	
	public EntityManagerFactory getEmf() {
		return emf;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public UserTransaction getTransaction() {
		return transaction;
	}

}
