package ch.bbcag.bubblegum.dao.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

import ch.bbcag.bubblegum.dao.querryoperation.QuerryOperation;

public interface IQueryExecutor<T> {

	public <E> E executeQuery(ExecutionUnit<EntityManager, E> executionUnit, QuerryOperation<E, T> querryOperation);
	
	public EntityManagerFactory getEmf();
	
	public EntityManager getEntityManager();
	
	public UserTransaction getTransaction();
}
