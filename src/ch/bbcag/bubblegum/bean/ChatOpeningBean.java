package ch.bbcag.bubblegum.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.bbcag.bubblegum.service.IChatService;

@Named
@RequestScoped
public class ChatOpeningBean {

	@Inject
	IChatService chatService;
	
	public void openQuiqChat(long userId) {
		chatService.
	}
}
