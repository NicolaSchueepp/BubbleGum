package ch.bbcag.bubblegum.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.bbcag.bubblegum.service.IInviteService;
import ch.bbcag.bubblegum.util.message.MessageArray;

@Named
@RequestScoped
public class AddUserBubbleBean {

	private Long userId;
	
	@Inject
	private ChatBean chatBean;
	
	@Inject
	private IInviteService inviteService;
	
	@Inject
	private MessageArray messageArray;
	
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
