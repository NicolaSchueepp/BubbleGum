package ch.bbcag.bubblegum.bean;

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
	
	public String getHash() {
		return conversationAccessService.getKeyHashForChat(Long.valueOf(getChatId()));
	}
	
	public String getChatId() {
		Map<String, String> parameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		return parameterMap.get("chatId");
	}
	
	public List<Message> getMessages(){
		return messageService.getByChatId(Long.valueOf(getChatId()));
	}
	
	public String getChatName() {
		
	}
}
