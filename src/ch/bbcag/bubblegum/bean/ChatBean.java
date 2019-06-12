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
	
	@Inject
	IChatService chatService;
	
	private String hash;
	private String chatId;
	
	public void prepare() {
		if(getChatId() == null || getHash() == null) {
			String location = chatId == null ? "search.xhtml" : "chatsOverview.xhtml";
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(location);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getHash() {
		if(hash == null) {
			hash = conversationAccessService.getKeyHashForChat(Long.valueOf(getChatId()));
		}
		return hash;
	}
	
	public String getChatId() {
		if(chatId != null) {
			return chatId;
		}
		Map<String, String> parameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if(parameterMap.containsKey("chatId")) {
			chatId = parameterMap.get("chatId");
		}else if(parameterMap.containsKey("userId")) {
			long id = chatService.getQuickChatId(Long.valueOf(parameterMap.get("userId")));
			if(id != 0l) {
				chatId = String.valueOf(id);
			}
		}
		return chatId;
	}
	
	public List<Message> getMessages(){
		return messageService.getMessages(Long.valueOf(getChatId()), getHash());
	}
	
	public String getChatName() {
		return chatService.getChatName(Long.valueOf(getChatId()));
	}
	
	public String getChatName(long id) {
		return chatService.getChatName(id);
	}

}
