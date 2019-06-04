package ch.bbcag.bubblegum.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.bbcag.bubblegum.model.User;
import ch.bbcag.bubblegum.service.ISearchService;

@Named
@RequestScoped
public class ChatsOverviewBean {
	
	private String query = "";
	private List<User> results;
	
	@Inject
	ISearchService searchService;
	
	public void setQuery(String query) {
		this.query = query;
	}
	
	public String getQuery() {
		return query;
	}
	
	public void search() {
		results = searchService.getUsersByName(query);
	}
	
	public List<User> getResults(){
		return results;
	}
}
