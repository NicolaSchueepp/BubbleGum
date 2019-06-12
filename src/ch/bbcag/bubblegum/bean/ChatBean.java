package ch.bbcag.bubblegum.bean;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ch.bbcag.bubblegum.model.Message;
import ch.bbcag.bubblegum.service.IChatService;
import ch.bbcag.bubblegum.service.IConversationAccessService;
import ch.bbcag.bubblegum.service.IMessageService;

@Named
@RequestScoped
public class ChatBean {

	@Inject
	private IConversationAccessService conversationAccessService;
	
	@Inject
	IMessageService messageService;
	
	private String hash;
	
	public String getHash() {
		if(hash == null) {
			hash = conversationAccessService.getKeyHashForChat(Long.valueOf(getChatId()));
			if (hash.isEmpty());
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("chatsOverview");
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
		}
		return hash;
	}
	
	public String getChatId() {
		Map<String, String> parameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		return parameterMap.get("chatId");
	}
	
	public List<Message> getMessages(){
		return messageService.getMessages(Long.valueOf(getChatId()), getHash());
	}


}
