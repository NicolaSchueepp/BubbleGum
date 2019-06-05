package ch.bbcag.bubblegum.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIInput;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import ch.bbcag.bubblegum.model.Chat;
import ch.bbcag.bubblegum.service.IChatService;

@Named
@RequestScoped
public class ChatsOverviewBean implements Serializable {
	private static final long serialVersionUID = 7027924866646797604L;

	private String query = "";
	private List<Chat> results;
	private boolean noResults;

	@Inject
	private transient IChatService chatService;

	public void setQuery(String query) {
		this.query = query;
	}

	public String getQuery() {
		return query;
	}

	public List<Chat> getResults() {
		search(null);
		return results;
	}

	public void search(AjaxBehaviorEvent event) {
		if (event != null) {
			UIInput theInput = (UIInput) event.getSource();
			this.query = (String) theInput.getValue();
		}
		if (query.isEmpty()) {
			results = chatService.getAllChats();
		} else {
			results = chatService.searchChatByName(query);
		}
	}

	public boolean isNoResults() {
		if (results.isEmpty())
			noResults = true;
		else
			noResults = false;
		return noResults;
	}

	public void setNoResults(boolean noResults) {
		this.noResults = noResults;
	}

}
