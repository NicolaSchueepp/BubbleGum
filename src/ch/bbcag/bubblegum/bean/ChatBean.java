package ch.bbcag.bubblegum.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.bbcag.bubblegum.model.Message;
import ch.bbcag.bubblegum.model.UserInChat;
import ch.bbcag.bubblegum.service.IChatService;
import ch.bbcag.bubblegum.service.IConversationAccessService;
import ch.bbcag.bubblegum.service.IMessageService;
import ch.bbcag.bubblegum.service.IUserInChatService;

@Named
@ViewScoped
public class ChatBean implements Serializable{

	private static final long serialVersionUID = -7591504596005185310L;

	@Inject
	private transient IConversationAccessService conversationAccessService;
	
	@Inject
	private transient IMessageService messageService;
	
	@Inject
	private transient IChatService chatService;
	
	@Inject
	private transient IUserInChatService userInChatService;
	
	private String hash;
	private String chatId;
	
	public void prepare() {
		if(getChatId() == null || getHash() == null) {
			String location = chatId == null ? "search.xhtml" : "chatsOverview.xhtml";
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(location);
			} catch (IOException e) {
				throw new RuntimeException(e);
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
	
	public String clear() {
		messageService.deleteMessages(Long.valueOf(getChatId()));
		return null;
	}
	
	public boolean isAdmin() {
		return chatService.isAdmin(Long.valueOf(getChatId()));
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
	
	public boolean isBubble() {
		return chatService.getChat(Long.valueOf(getChatId())).isBubble();
	}
	
	public List<UserInChat> getPartipiants(){
		return userInChatService.getUsers(Long.valueOf(getChatId()));
	}

}
