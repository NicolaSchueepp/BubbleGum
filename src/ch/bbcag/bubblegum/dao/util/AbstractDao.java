package ch.bbcag.bubblegum.dao.util;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ch.bbcag.bubblegum.dao.querryoperation.ListReadOperation;
import ch.bbcag.bubblegum.dao.querryoperation.QuerryOperation;
import ch.bbcag.bubblegum.dao.querryoperation.ReadOperation;
import ch.bbcag.bubblegum.dao.querryoperation.SingleReadOperation;
import ch.bbcag.bubblegum.dao.querryoperation.WriteOperation;

public abstract class AbstractDao<T> {
	
	@Inject
	protected QueryExecutor<T> queryExecutor;
	
	
	public void create(T t) {
		queryExecutor.executeQuery(new ExecutionUnit<EntityManager, Void>() {
			@Override
			public Void execute(EntityManager entityManager) {
				entityManager.persist(t);
				return null;
			}
		}, new WriteOperation<T>());
	}
	
	public T getById(long id) {
		return queryExecutor.executeQuery(new ExecutionUnit<EntityManager, T>() {
			@Override
			public T execute(EntityManager entityManager) {
				return entityManager.find(getClazz(), id);
			}
		}, new SingleReadOperation<T>());
	}
	
	public List<T> getAll(){
		return executeCustomQuarry("SELECT x FROM "+getClassName()+" x", null, new ListReadOperation<T>());
	}
	
	public void update(T t) {
		queryExecutor.executeQuery(new ExecutionUnit<EntityManager, Void>() {
			@Override
			public Void execute(EntityManager entityManager) {
				entityManager.merge(t);
				return null;
			}
		},new WriteOperation<T>());
	}
	
	public void delete(T t) {
		executeCustomQuarry("DELETE FROM "+getClassName()+" x WHERE x = :obj", (q) -> q.setParameter("obj", t), new WriteOperation<T>());
	}
	
	public <E> E executeCustomQuarry(String querry, ExecutionUnit<TypedQuery<T>, TypedQuery<T>> paramSetter, QuerryOperation<E, T> querryOperation){
		return queryExecutor.executeQuery(new ExecutionUnit<EntityManager, E>() {
			@Override
			public E execute(EntityManager entityManager) {
				TypedQuery<T> query = entityManager.createQuery(querry, getClazz());
				if(paramSetter!=null)
					paramSetter.execute(query);
				return querryOperation.extractFromQuarry(query);
			}
		}, querryOperation);
	}
	
	public abstract Class<T> getClazz();
	
	public String getClassName() {
		return getClazz().getSimpleName();
	}
}
