package ch.bbcag.bubblegum.dao;

import java.util.List;

import ch.bbcag.bubblegum.dao.querryoperation.ListReadOperation;
import ch.bbcag.bubblegum.dao.querryoperation.SingleReadOperation;
import ch.bbcag.bubblegum.dao.util.AbstractDao;
import ch.bbcag.bubblegum.model.Invite;

public class InviteDao extends AbstractDao<Invite> implements IInviteDao{
	

	@Override
	public List<Invite> getUnacceptedByUser(long userId) {
		return executeCustomQuarry("SELECT i FROM Invite i where i.invitedtId = :invitedtId AND i.isAccepted = false", 
				(q) -> q.setParameter("invitedtId", userId), new ListReadOperation<Invite>());
	}

	@Override
	public Invite getByUserAndChatId(long userId, long chatId) {
		return executeCustomQuarry("SELECT i FROM Invite i where i.invitedtId = :invitedtId AND i.chatId = :chatId",
				(q) -> q.setParameter("invitedtId", userId).setParameter("chatId", chatId), new SingleReadOperation<Invite>());
	}

	@Override
	public Class<Invite> getClazz() {
		return Invite.class;
	}

}
