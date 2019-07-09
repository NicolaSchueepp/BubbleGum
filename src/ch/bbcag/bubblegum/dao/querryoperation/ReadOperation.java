package ch.bbcag.bubblegum.dao.querryoperation;

import ch.bbcag.bubblegum.dao.util.IQueryExecutor;
import ch.bbcag.bubblegum.dao.util.QueryExecutor;

public abstract class ReadOperation<R, T> implements QuerryOperation<R, T>{

	@Override
	public void prepare(IQueryExecutor<T> executor) {}
	
	@Override
	public void close(IQueryExecutor<T> executor) {
		executor.getEntityManager().clear();
		executor.getEmf().getCache().evictAll();
		executor.getEntityManager().close();
	}
}
