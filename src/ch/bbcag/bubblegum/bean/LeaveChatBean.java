package ch.bbcag.bubblegum.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.bbcag.bubblegum.service.IUserInChatService;

@Named
@RequestScoped
public class LeaveChatBean {
	
	private long chatId;

	@Inject
	private IUserInChatService userInChatService;
	
	public void setChatId(long chatId) {
		this.chatId = chatId;
	}
	
	public long getChatId() {
		return chatId;
	}
	
	public String leave() {
		userInChatService.removeSelf(chatId);
		return "chatsOverview";
	}
}
