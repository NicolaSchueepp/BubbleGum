package ch.bbcag.bubblegum.dao;

import java.util.List;

import ch.bbcag.bubblegum.model.User;

public interface IUserDao {
	
	public User create(User user);
	
	public User getUserByEmail(String email);
	
	public User getUserById(Long id);
	
	public List<User> searchUserByName(String name);
}