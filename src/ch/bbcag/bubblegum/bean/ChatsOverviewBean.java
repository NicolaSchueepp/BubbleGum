package ch.bbcag.bubblegum.bean;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.bbcag.bubblegum.model.UserInChat;
import ch.bbcag.bubblegum.service.IChatService;
import ch.bbcag.bubblegum.service.IUserInChatService;

@Named
@ViewScoped
public class ChatsOverviewBean implements Serializable {

	private static final long serialVersionUID = 3026672542390141361L;

	private String query = "";
	private List<UserInChat> results;
	private List<UserInChat> cachedResults;
	private boolean noResults;

	@Inject
	private transient IUserInChatService userInChatService;

	@Inject
	private transient IChatService chatService;

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
		if (results == null) {
			cachedResults = userInChatService.getAllChats();
			results = cachedResults;
		}

		if (!query.isEmpty()) {
			results = cachedResults.stream().filter(
					c -> chatService.getChatName(c.getChat().getId()).toUpperCase().contains(query.toUpperCase()))
					.collect(Collectors.toList());
		} else {
			results = cachedResults;
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
