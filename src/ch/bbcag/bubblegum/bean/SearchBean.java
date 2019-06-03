package ch.bbcag.bubblegum.bean;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ch.bbcag.bubblegum.model.User;
import ch.bbcag.bubblegum.service.ISearchService;

@Named
@RequestScoped
public class SearchBean {
	
	private String query = "Name";
	
	@Inject
	ISearchService searchService;

	public void setQuery(String query) {
		this.query = query;
	}
	
	public String getQuery() {
		return query;
	}
	
	public String search() {
		
	    FacesContext ctx  = FacesContext.getCurrentInstance();
	    return ctx.getViewRoot().getViewId();
	}
	
	public List<User> getResults(){
		return searchService.getUsersByName(query);
	}
}
