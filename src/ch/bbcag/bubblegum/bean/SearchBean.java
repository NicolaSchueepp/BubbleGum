package ch.bbcag.bubblegum.bean;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ch.bbcag.bubblegum.model.User;
import ch.bbcag.bubblegum.service.ISearchService;
import ch.bbcag.bubblegum.util.message.MessageArray;

@Named
@RequestScoped
public class SearchBean {
	
	private String query = "";
	private List<User> results;
	
	@Inject
	ISearchService searchService;

	@Inject
	MessageArray messageArray;
	
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
