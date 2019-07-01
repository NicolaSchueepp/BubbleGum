package ch.bbcag.bubblegum.service;

import ch.bbcag.bubblegum.model.User;

public interface IMailService {

	public void sendAuthenticationKey(User toUser);

	public void sendMail(String toMail, String toName, String subject, String message);
	
	public void verifyEmail(String key);
}
