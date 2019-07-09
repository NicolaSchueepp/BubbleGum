package ch.bbcag.bubblegum.dao.test.util;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.Alternative;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import ch.bbcag.bubblegum.dao.querryoperation.QuerryOperation;
import ch.bbcag.bubblegum.dao.util.DaoException;
import ch.bbcag.bubblegum.dao.util.ExecutionUnit;
import ch.bbcag.bubblegum.dao.util.IQueryExecutor;

@Alternative
public class QuerryExecutorForTest<T> implements IQueryExecutor<T>{

	private EntityManagerFactory emf;
	
	private UserTransaction transaction;
	
	private EntityManager entityManager;
	
	public QuerryExecutorForTest() {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("javax.persistence.jdbc.user", "root");
		properties.put("javax.persistence.jdbc.password", "1234");
		properties.put("javax.persistence.jdbc.databaseName", "bubblegum");
		properties.put("javax.persistence.jdbc.serverName", "localhost");
		properties.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost/bubblegum");
		properties.put("javax.persistence.jdbc.driver", "javax.sql.DataSource");
		emf = Persistence.createEntityManagerFactory("BubbleGum", properties);
	}

	@Override
	public <E> E executeQuery(ExecutionUnit<EntityManager, E> executionUnit, QuerryOperation<E, T> querryOperation) {
		this.entityManager = emf.createEntityManager();
		transaction = new UserTransactionAdapter(entityManager.getTransaction());
		E result;
		try {
			querryOperation.prepare(this);
			result = executionUnit.execute(entityManager);
			querryOperation.close(this);
			if (transaction.getStatus() == Status.STATUS_ACTIVE) {
				transaction.rollback();
			}
		} catch (NoResultException e) {
			result = null;
		} catch (IllegalStateException | SystemException | SecurityException e) {
			throw new DaoException(e);
		}
		return result;
	}

	@Override
	public EntityManagerFactory getEmf() {
		return emf;
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public UserTransaction getTransaction() {
		return transaction;
	}
}
