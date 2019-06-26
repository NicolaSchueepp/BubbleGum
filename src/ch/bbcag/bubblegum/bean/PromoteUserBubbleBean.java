package ch.bbcag.bubblegum.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.bbcag.bubblegum.service.IChatService;
import ch.bbcag.bubblegum.service.IInviteService;
import ch.bbcag.bubblegum.service.IUserInChatService;
import ch.bbcag.bubblegum.util.message.MessageArray;

@Named
@RequestScoped
public class PromoteUserBubbleBean {

	private Long userId;
	
	@Inject
	ChatBean chatBean;
	
	@Inject
	IUserInChatService userInChatService;
	
	@Inject
	MessageArray messageArray;
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public String promote() {
		userInChatService.promote(Long.valueOf(chatBean.getChatId()), userId);
		return null;
	}
}
