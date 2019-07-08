package ch.bbcag.bubblegum.dao.test.util;

import javax.mail.MethodNotSupportedException;
import javax.persistence.EntityTransaction;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

public class UserTransactionAdapter implements UserTransaction{

	private EntityTransaction entityTransaction;
	
	public UserTransactionAdapter(EntityTransaction entityTransaction) {
		this.entityTransaction = entityTransaction;
	}
	
	@Override
	public void begin() throws NotSupportedException, SystemException {
		entityTransaction.begin();
	}

	@Override
	public void commit() throws RollbackException, HeuristicMixedException, HeuristicRollbackException,
			SecurityException, IllegalStateException, SystemException {
		entityTransaction.commit();
	}

	@Override
	public int getStatus() throws SystemException {
		return entityTransaction.isActive() ? Status.STATUS_ACTIVE : Status.STATUS_UNKNOWN;
	}

	@Override
	public void rollback() throws IllegalStateException, SecurityException, SystemException {
		entityTransaction.rollback();
	}

	@Override
	public void setRollbackOnly() throws IllegalStateException, SystemException {
		entityTransaction.setRollbackOnly();
	}

	@Override
	public void setTransactionTimeout(int seconds) throws SystemException {
		throw new UnsupportedOperationException();
	}

}
