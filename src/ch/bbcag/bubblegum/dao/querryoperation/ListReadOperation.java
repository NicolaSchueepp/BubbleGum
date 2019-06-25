package ch.bbcag.bubblegum.dao.querryoperation;

import java.util.List;

import javax.persistence.TypedQuery;

public class ListReadOperation<T> extends ReadOperation<List<T>, T>{

	@Override
	public List<T> extractFromQuarry(TypedQuery<T> query) {
		return query.getResultList();
	}

}
