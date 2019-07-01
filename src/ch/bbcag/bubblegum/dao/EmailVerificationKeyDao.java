package ch.bbcag.bubblegum.dao;

import ch.bbcag.bubblegum.dao.querryoperation.SingleReadOperation;
import ch.bbcag.bubblegum.dao.util.AbstractDao;
import ch.bbcag.bubblegum.model.EmailVerificationKey;

public class EmailVerificationKeyDao extends AbstractDao<EmailVerificationKey> implements IEmailVerificationKeyDao {

	@Override
	public EmailVerificationKey getByKey(String hash) {
		return executeCustomQuarry("SELECT e FROM EmailVerificationKey e WHERE e.hash = :hash", (q) -> q.setParameter("hash", hash), new SingleReadOperation<EmailVerificationKey>());
	}

	@Override
	public EmailVerificationKey getByUserId(long userId) {
		return executeCustomQuarry("SELECT e FROM EmailVerificationKey e WHERE e.userId = :userId", (q) -> q.setParameter("userId", userId), new SingleReadOperation<EmailVerificationKey>());
	}
	
	@Override
	public Class<EmailVerificationKey> getClazz() {
		return EmailVerificationKey.class;
	}

}
