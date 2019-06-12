package ch.bbcag.bubblegum.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.bbcag.bubblegum.service.IChatService;
import ch.bbcag.bubblegum.util.message.Message;
import ch.bbcag.bubblegum.util.message.MessageArray;
import ch.bbcag.bubblegum.util.message.MessageStyle;

@Named
@RequestScoped
public class ChatOpeningBean {

	@Inject
	IChatService chatService;

	@Inject
	MessageArray messageArray;
	
	public String openQuiqChat(String userId) {
		long id = chatService.getQuickChatId(Long.valueOf(userId));
		if(id != 0l) 
			return "chat?faces-redirect=true&chatId=" + id;
		return "chatsOverview";
	}

}
