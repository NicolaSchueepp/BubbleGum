package ch.bbcag.bubblegum.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.bbcag.bubblegum.service.IInviteService;
import ch.bbcag.bubblegum.service.IUserInChatService;
import ch.bbcag.bubblegum.util.message.MessageArray;

@Named
@RequestScoped
public class RemoveUserBubbleBean {

private Long userId;
	
	@Inject
	ChatBean chatBean;
	
	@Inject
	IUserInChatService userInChatService;

	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public String remove() {
		userInChatService.removeUser(Long.valueOf(chatBean.getChatId()), userId);
		return null;
	}
}
