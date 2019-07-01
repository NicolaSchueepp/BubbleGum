package ch.bbcag.bubblegum.dao;

import ch.bbcag.bubblegum.model.EmailVerificationKey;

public interface IEmailVerificationKeyDao {

	public void create(EmailVerificationKey emailVerificationKey);
	
	public EmailVerificationKey getByKey(String key);
	
	public EmailVerificationKey getByUserId(long userId);
	
	public void delete(EmailVerificationKey emailVerificationKey);
}
