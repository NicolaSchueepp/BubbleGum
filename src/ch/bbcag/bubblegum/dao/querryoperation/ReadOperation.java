package ch.bbcag.bubblegum.dao.querryoperation;

import ch.bbcag.bubblegum.dao.util.QueryExecutor;

public abstract class ReadOperation<R, T> implements QuerryOperation<R, T>{

	@Override
	public void prepare(QueryExecutor<T> executor) {}
	
	@Override
	public void close(QueryExecutor<T> executor) {
		executor.getEntityManager().clear();
		executor.getEmf().getCache().evictAll();
		executor.getEntityManager().close();
	}
}
