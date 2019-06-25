package ch.bbcag.bubblegum.dao;

import java.util.List;

import ch.bbcag.bubblegum.model.User;

public interface IUserDao {
	
	public void create(User user);
	
	public User getUserByEmail(String email);
	
	public User getById(long id);
	
	public List<User> searchUserByName(String name);
	
	public void update(User user);
}