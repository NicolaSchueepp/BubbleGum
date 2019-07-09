package ch.bbcag.bubblegum.service;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ch.bbcag.bubblegum.dao.IEmailVerificationKeyDao;
import ch.bbcag.bubblegum.dao.IUserDao;
import ch.bbcag.bubblegum.model.EmailVerificationKey;
import ch.bbcag.bubblegum.model.User;
import ch.bbcag.bubblegum.util.LogInitializer;
import ch.bbcag.bubblegum.util.Util;
import ch.bbcag.bubblegum.util.message.MessageArray;
import ch.bbcag.bubblegum.util.message.MessageStyle;

public class MailService implements IMailService{

	private static final String FROM_MAIL = "bubblegum.bbcag@gmail.com";
	private static final String PASSWORD = "PW4Ketschgummi";
	private static final String HOST_NAME = "smtp.gmail.com";
	private static final int PORT = 587;

	@Resource(name = "Gmail")
	private Session mailSession;
	
	@Inject
	private MessageArray messageArray;
	
	@Inject
	private IEmailVerificationKeyDao emailVerificationKeyDao;
	
	@Inject
	private IUserDao userDao;
	
	private final Logger LOGGER = new LogInitializer(getClass().getName()).initConsole().getLogger();
	
	@Override
	public void sendAuthenticationKey(User toUser) {
		EmailVerificationKey verificationKey;
		if((verificationKey = emailVerificationKeyDao.getByUserId(toUser.getId())) != null) {
			emailVerificationKeyDao.delete(verificationKey);
		}
		verificationKey = new EmailVerificationKey();
		String hash = Util.generateKey(toUser.getEmail());
		verificationKey.setCreationDate(System.currentTimeMillis());
		verificationKey.setHash(hash);
		verificationKey.setUserId(toUser.getId());
		emailVerificationKeyDao.create(verificationKey);
		sendMail(toUser.getEmail(), toUser.getName(), "Bitte bestätigen sie Ihre Emailadresse",
				"Grüezi "+ toUser.getName()+ "\n Bitte bestätigen sie Ihre Emailadresse in dem sie folgenden link öffnen:\nhttp://localhost:8080/BubbleGum/verifyEmail.xhtml?key=" + hash);
		LOGGER.log(Level.FINEST, "AuthenticationKeyMail send to " + toUser.getId());
		messageArray.addMessage(new ch.bbcag.bubblegum.util.message.Message(MessageStyle.Info, "Eine Mail zur bestätigung Ihrer Emailadresse wurde ihnen zu gesant"));
	}

	
	@Override
	public void sendMail(String toMail, String toName, String subject, String message) {
		try {
			Message msg = new MimeMessage(mailSession);
			msg.setSubject(subject);
			msg.setRecipient(RecipientType.TO, new InternetAddress(toMail, toName));
			msg.setFrom(new InternetAddress(FROM_MAIL, "Bubble Gum"));
			msg.setText(message);
			
			Transport.send(msg);
		} catch (UnsupportedEncodingException | MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void verifyEmail(String key) {
		EmailVerificationKey verificationKey = emailVerificationKeyDao.getByKey(key);
		if(verificationKey == null || verificationKey.getCreationDate()+600000<System.currentTimeMillis()) {
			messageArray.addMessage(new ch.bbcag.bubblegum.util.message.Message(MessageStyle.error,"Der Verification Key ist ungültig oder abgeloffen"));
			emailVerificationKeyDao.delete(verificationKey);
			LOGGER.log(Level.FINEST, "invalid AuthenticationKey " + key);
			return;
		}
		User user = userDao.getById(verificationKey.getUserId());
		user.setEmailVerified(true);
		userDao.update(user);
		emailVerificationKeyDao.delete(verificationKey);
		LOGGER.log(Level.FINEST, "Mail successfull authenticatet by user " + user.getId());
		messageArray.addMessage(new ch.bbcag.bubblegum.util.message.Message(MessageStyle.Info,"Email wurde erfolgreich bestätigt"));
	}

}
