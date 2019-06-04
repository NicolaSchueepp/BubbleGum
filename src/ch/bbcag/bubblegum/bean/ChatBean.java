package ch.bbcag.bubblegum.bean;

import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ch.bbcag.bubblegum.service.IConversationAccessService;

@Named
@RequestScoped
public class ChatBean {

	@Inject
	private IConversationAccessService conversationAccessService;
	
	public String getKey() {
		return conversationAccessService.create(Long.valueOf(getChatId()));
	}
	
	public String getChatId() {
		Map<String, String> parameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		return parameterMap.get("chatId");
	}
}
