package ch.bbcag.bubblegum.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import ch.bbcag.bubblegum.dao.util.QueryExecutionUnit;
import ch.bbcag.bubblegum.dao.util.QueryExecutor;
import ch.bbcag.bubblegum.model.Invite;
import ch.bbcag.bubblegum.model.User;

public class InviteDao implements IInviteDao{
	
	@Inject
	private QueryExecutor queryExecutor;


	@Override
	public Invite create(Invite invite) {
		queryExecutor.create(new QueryExecutionUnit<Invite>() {
			@Override
			public Invite execute(EntityManager entityManager, QueryExecutor queryExecutor) throws NoResultException, NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
				queryExecutor.prepareWrite();
				entityManager.persist(invite);
				queryExecutor.closeWrite();
				return invite;
			}
		});
		try {
			return queryExecutor.executeQuery();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Invite> getUnacceptedByUser(long userId) {
		queryExecutor.create(new QueryExecutionUnit<List<Invite>>() {
			@Override
			public List<Invite> execute(EntityManager entityManager, QueryExecutor queryExecutor) throws NoResultException, NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
				queryExecutor.prepareRead();
				TypedQuery<Invite> query = entityManager.createQuery("SELECT i FROM Invite i where i.invitedtId = :invitedtId AND i.isAccepted = false",
						Invite.class);
				query.setParameter("invitedtId", userId);

				List<Invite> invites = query.getResultList();
				queryExecutor.closeRead();
				return invites;
			}
		});
		try {
			return queryExecutor.executeQuery();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Invite getByUserAndChatId(long userId, long chatId) {
		queryExecutor.create(new QueryExecutionUnit<Invite>() {
			@Override
			public Invite execute(EntityManager entityManager, QueryExecutor queryExecutor) throws NoResultException, NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
				queryExecutor.prepareRead();
				TypedQuery<Invite> query = entityManager.createQuery("SELECT i FROM Invite i where i.invitedtId = :invitedtId AND i.chatId = :chatId",
						Invite.class);
				query.setParameter("invitedtId", userId);
				query.setParameter("chatId", chatId);
				Invite invite = query.getSingleResult();
				queryExecutor.closeRead();
				return invite;
			}
		});
		try {
			return queryExecutor.executeQuery();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Invite update(Invite invite) {
		queryExecutor.create(new QueryExecutionUnit<Invite>() {
			@Override
			public Invite execute(EntityManager entityManager, QueryExecutor queryExecutor) throws NoResultException, NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
				queryExecutor.prepareWrite();
				entityManager.merge(invite);
				queryExecutor.closeWrite();
				return invite;
			}
		});
		try {
			return queryExecutor.executeQuery();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Invite getById(long id) {
		queryExecutor.create(new QueryExecutionUnit<Invite>() {
			@Override
			public Invite execute(EntityManager entityManager, QueryExecutor queryExecutor) throws NoResultException, NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
				queryExecutor.prepareRead();
				TypedQuery<Invite> query = entityManager.createQuery("SELECT i FROM Invite i where i.id = :id",
						Invite.class);
				query.setParameter("id", id);
				Invite invite = query.getSingleResult();
				queryExecutor.closeRead();
				return invite;
				
			}
		});
		try {
			return queryExecutor.executeQuery();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}



}
