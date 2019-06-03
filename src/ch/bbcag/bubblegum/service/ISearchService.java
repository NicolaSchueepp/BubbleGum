package ch.bbcag.bubblegum.service;

import java.util.List;

import ch.bbcag.bubblegum.model.User;

public interface ISearchService {

	public List<User> getUsersByName(String name);
}
