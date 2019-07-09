package ch.bbcag.bubblegum.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.bbcag.bubblegum.service.IInviteService;

@Named
@RequestScoped
public class AcceptInviteBean {

	private long inviteId;
	private long chatId;
	
	@Inject
	private IInviteService inviteService;
	
	public void setInviteId(long inviteId) {
		this.inviteId = inviteId;
	}
	
	public long getInviteId() {
		return inviteId;
	}
	
	public long getChatId() {
		return chatId;
	}
	
	public void setChatId(long chatId) {
		this.chatId = chatId;
	}
	
	public String accept() {
		if (inviteService.acceptInvite(inviteId))
			return "chat?faces-redirect=true&chatId="+chatId;
		return "home";
	}
}
