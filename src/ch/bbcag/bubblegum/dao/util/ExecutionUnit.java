package ch.bbcag.bubblegum.dao.util;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

@FunctionalInterface
public interface ExecutionUnit<I,O> {

	public O execute(I input);
}
