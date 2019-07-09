package ch.bbcag.bubblegum.dao.querryoperation;

import javax.persistence.TypedQuery;

public class SingleReadOperation<T> extends ReadOperation<T, T>{

	@Override
	public T extractFromQuarry(TypedQuery<T> query) {
		return query.getSingleResult();
	}
}
