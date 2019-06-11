package ch.bbcag.bubblegum.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.bbcag.bubblegum.service.IChatService;
import ch.bbcag.bubblegum.util.message.MessageArray;

@Named
@RequestScoped
public class ChatOpeningBean {

	@Inject
	IChatService chatService;

	@Inject
	MessageArray messageArray;
	
	private String text;
	
	public String openQuiqChat() {
//		String value = FacesContext.getCurrentInstance().
//				getExternalContext().getRequestParameterMap().get("userId");
		System.err.println("-----------------------------------------------------------------" + text);
		throw new RuntimeException();
		
//		return "home";
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}

}
