package ch.bbcag.bubblegum.service;

import java.util.List;

import ch.bbcag.bubblegum.model.User;

public interface IUserService {

	public boolean register(String name, String email, String password);

	public boolean login(String email, String password);
	
	public List<User> searchUsersByName(String name);
}
