package ch.bbcag.bubblegum.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.bbcag.bubblegum.model.UserInChat;
import ch.bbcag.bubblegum.service.IUserInChatService;

@Named
@RequestScoped
public class ChatsOverviewBean {

	private String query = "";
	private List<UserInChat> results;
	private boolean noResults;

	@Inject
	private transient IUserInChatService userInChatService;

	public void setQuery(String query) {
		this.query = query;
	}

	public String getQuery() {
		return query;
	}

	public List<UserInChat> getResults() {
		search();
		return results;
	}

	public void search() {
		if (query.isEmpty()) {
			results = userInChatService.getAllChats();
		} else {
			results = userInChatService.searchChatByName(query);
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
