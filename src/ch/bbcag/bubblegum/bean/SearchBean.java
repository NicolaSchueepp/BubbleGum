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
	
	@Inject
	IUserService userService;
	
	public void setQuery(String query) {
		this.query = query;
	}
	
	public String getQuery() {
		return query;
	}
	
	public void search() {
		results = userService.searchUsersByName(query);
	}
	
	public List<User> getResults(){
		return results;
	}
}
