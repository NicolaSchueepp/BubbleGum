package ch.bbcag.bubblegum.service;

import javax.inject.Inject;

import ch.bbcag.bubblegum.bean.SessionBean;
import ch.bbcag.bubblegum.dao.IInviteDao;
import ch.bbcag.bubblegum.dao.IUserInChatDao;
import ch.bbcag.bubblegum.model.Invite;
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
	
	@Inject
	private MessageArray messageArray;
	
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
		Invite invite = new Invite();
		invite.setAccepted(false);
		invite.setChatId(chatId);
		invite.setInvitedtId(userId);
		invite.setSenderId(sessionBean.getUserID());
		inviteDao.create(invite);
		messageArray.addMessage(new Message(MessageStyle.Warning,"Nutzer erfolgreich eingeladen"));
		return true;
	}

	@Override
	public boolean acceptInvite(long inviteId) {
		Invite invite = inviteDao.getById(inviteId);
		invite.setAccepted(true);
		if (userInChatService.addUser(invite.getChatId(), invite.getInvitedtId(), false)) {
			messageArray.addMessage(new Message(MessageStyle.Warning,"Nutzer erfolgreich eingeladen"));
			return true;
		}
		return false;
			
	}

}
