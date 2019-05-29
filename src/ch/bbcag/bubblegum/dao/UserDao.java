package ch.bbcag.bubblegum.dao;

import ch.bbcag.bubblegum.model.User;

public interface UserDao {
	
	public User create();
	
	public User getUserByEmail();
	
	public User getUserById();
	
	public User searchUserByName();
}