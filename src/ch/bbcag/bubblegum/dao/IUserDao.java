package ch.bbcag.bubblegum.dao;

import ch.bbcag.bubblegum.model.User;

public interface IUserDao {
	
	public User create(User user);
	
	public User getUserByEmail(String email);
	
	public User getUserById(Long id);
	
	public User searchUserByName();
}