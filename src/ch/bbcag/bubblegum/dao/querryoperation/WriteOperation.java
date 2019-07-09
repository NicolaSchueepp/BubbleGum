package ch.bbcag.bubblegum.dao.querryoperation;

import javax.persistence.TypedQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import ch.bbcag.bubblegum.dao.util.DaoException;
import ch.bbcag.bubblegum.dao.util.IQueryExecutor;

public class WriteOperation<T> implements QuerryOperation<Void, T> {

	@Override
	public Void extractFromQuarry(TypedQuery<T> query) {
		query.executeUpdate();
		return null;
	}

	@Override
	public void prepare(IQueryExecutor<T> executor) {
		try {
			executor.getTransaction().begin();
			executor.getEntityManager().joinTransaction();
		} catch (NotSupportedException | SystemException e) {
			throw new DaoException(e);
		}

	}

	@Override
	public void close(IQueryExecutor<T> executor) {
		try {
			executor.getEntityManager().flush();
			executor.getTransaction().commit();
			executor.getEntityManager().close();
		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
				| HeuristicRollbackException | SystemException e) {
			throw new DaoException(e);
		}
	}

}
