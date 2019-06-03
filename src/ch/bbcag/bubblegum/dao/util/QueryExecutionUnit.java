package ch.bbcag.bubblegum.dao.util;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

public interface QueryExecutionUnit<T> {

	public T execute(EntityManager entityManager, QueryExecutor quarryExecutor) throws NoResultException, NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException;
}
