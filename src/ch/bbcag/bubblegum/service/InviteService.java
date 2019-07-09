package ch.bbcag.bubblegum.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import ch.bbcag.bubblegum.bean.SessionBean;
import ch.bbcag.bubblegum.dao.IInviteDao;
import ch.bbcag.bubblegum.dao.IUserInChatDao;
import ch.bbcag.bubblegum.model.Invite;
import ch.bbcag.bubblegum.util.LogInitializer;
import ch.bbcag.bubblegum.util.message.Message;
import ch.bbcag.bubblegum.util.message.MessageArray;
import ch.bbcag.bubblegum.util.message.MessageStyle;

public class InviteService implements IInviteService {

	@Inject
	private IInviteDao inviteDao;
	
	@Inject
	private SessionBean sessionBean;
	
	@Inject
	private IUserInChatService userInChatService;

	private @Inject 
	IUserInChatDao userInChatDao;
	
	@Inject
	private MessageArray messageArray;
	
	private final Logger LOGGER = new LogInitializer(getClass().getName()).initConsole().getLogger();
	
	@Override
	public boolean inviteUser(long chatId, long userId) {
		if(inviteDao.getByUserAndChatId(userId, chatId) != null) {
			messageArray.addMessage(new Message(MessageStyle.Warning,"Dieser Nutzer wurde bereits eingeladen"));
			return false;
		}
		if(userId == sessionBean.getUserID()) {
			messageArray.addMessage(new Message(MessageStyle.Warning,"Du kannst dich nicht selber einladen"));
			return false;
		}
		if(!userInChatDao.getByUserIdAndChatId(sessionBean.getUserID(), chatId).isAdmin()) {
			messageArray.addMessage(new Message(MessageStyle.error,"Du hast keine Berechtigung für diese Aktion"));
			LOGGER.log(Level.FINEST, "Invalid admin access by " + sessionBean.getUserID() + " in chat " + chatId);
			return false;
		}
		Invite invite = new Invite();
		invite.setAccepted(false);
		invite.setChatId(chatId);
		invite.setInvitedtId(userId);
		invite.setSenderId(sessionBean.getUserID());
		inviteDao.create(invite);
		messageArray.addMessage(new Message(MessageStyle.Info,"Nutzer erfolgreich eingeladen"));
		LOGGER.log(Level.FINEST, "Invite send for chat " + chatId + " for user " + userId + " by user " + sessionBean.getUserID());
		return true;
	}

	@Override
	public boolean acceptInvite(long inviteId) {
		Invite invite = inviteDao.getById(inviteId);
		invite.setAccepted(true);
		inviteDao.update(invite);
		if (userInChatService.addUser(invite.getChatId(), invite.getInvitedtId(), false)) {
			messageArray.addMessage(new Message(MessageStyle.Info,"Du bist der Bubble beigetreten"));
			LOGGER.log(Level.FINEST, "User " + sessionBean.getUserID() + " accepted invite " + inviteId);
			return true;
		}
		return false;
			
	}

	@Override
	public List<Invite> getUnacceptedInvites() {
		return inviteDao.getUnacceptedByUser(sessionBean.getUserID());
	}

}
