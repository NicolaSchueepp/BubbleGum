package ch.bbcag.bubblegum.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.bbcag.bubblegum.service.IChatService;

@Named
@RequestScoped
public class BubbleCreationBean {
	
	@Inject
	private IChatService chatService;
	
	private String name;
	
	public String createBubble() {
		long id = chatService.createBubble(name);
		return "chat?faces-redirect=true&chatId="+id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
