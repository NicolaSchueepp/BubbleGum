package ch.bbcag.bubblegum.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.UICommand;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.sun.xml.internal.ws.api.Component;

import ch.bbcag.bubblegum.model.Chat;
import ch.bbcag.bubblegum.service.IChatService;

@Named
@SessionScoped
public class ChatsOverviewBean implements Serializable {
	private static final long serialVersionUID = 7027924866646797604L;

	private String query = "";
	private List<Chat> results;

	@Inject
	private transient IChatService chatService;

	public void setQuery(String query) {
		this.query = query;
	}

	public String getQuery() {
		return query;
	}

	public List<Chat> getResults() {
		search();
		return results;
	}

	public void search(AjaxBehaviorEvent event) {
		if (event != null) {
			
		}
		if (query.isEmpty()) {
			results = chatService.searchAllChats();
		} else {
			results = chatService.searchChatByName(query);
		}
	}

	private void search() {
		search(null);
	}
}
