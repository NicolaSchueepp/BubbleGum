package ch.bbcag.bubblegum.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.bbcag.bubblegum.model.User;
import ch.bbcag.bubblegum.service.IUserService;

@Named
@RequestScoped
public class SearchBean {

	private String query = "";
	private List<User> results;
	private boolean noResults;

	@Inject
	private IUserService userService;

	public void setQuery(String query) {
		this.query = query;
	}

	public String getQuery() {
		return query;
	}

	public void search() {
		results = userService.searchUsersByName(query);
	}

	public List<User> getResults() {
		if (results != null && results.isEmpty()) {
			noResults = true;
		} else {
			noResults = false;
		}
		if (isEmptyQuery()) {
			noResults = false;
		}
		return results;
	}

	public boolean isNoResults() {
		return noResults;
	}

	public void setNoResults(boolean noResults) {
		this.noResults = noResults;
	}

	public boolean isEmptyQuery() {
		return query.isEmpty();
	}
}
