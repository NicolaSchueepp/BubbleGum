package ch.bbcag.bubblegum.dao.querryoperation;

import javax.persistence.TypedQuery;

import ch.bbcag.bubblegum.dao.util.IQueryExecutor;
import ch.bbcag.bubblegum.dao.util.QueryExecutor;

public interface QuerryOperation<R,T> {

	public R extractFromQuarry(TypedQuery<T> query);
	
	public void prepare(IQueryExecutor<T> executor);
	
	public void close(IQueryExecutor<T> executor);
}
