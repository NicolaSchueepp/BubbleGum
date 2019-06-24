package ch.bbcag.bubblegum.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.bbcag.bubblegum.service.ChatService;
import ch.bbcag.bubblegum.service.IChatService;
import ch.bbcag.bubblegum.service.IInviteService;
import ch.bbcag.bubblegum.service.IUserInChatService;
import ch.bbcag.bubblegum.service.InviteService;
import ch.bbcag.bubblegum.util.message.Message;
import ch.bbcag.bubblegum.util.message.MessageArray;
import ch.bbcag.bubblegum.util.message.MessageStyle;

@Named
@RequestScoped
public class AddUserBubbleBean {

	private Long userId;
	
	@Inject
	ChatBean chatBean;
	
	@Inject
	IInviteService inviteService;
	
	@Inject
	MessageArray messageArray;
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public String add() {
		inviteService.inviteUser(Long.valueOf(chatBean.getChatId()), userId);
		return null;
	}
	
}
