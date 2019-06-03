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
	
	public String search() {
		results = searchService.getUsersByName(query);
	    FacesContext ctx  = FacesContext.getCurrentInstance();
	    return ctx.getViewRoot().getViewId();
	}
	
	public List<User> getResults(){
		return results;
	}
}
