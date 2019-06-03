package ch.bbcag.bubblegum.service;

public interface UserService {

	public boolean register(String name, String email, String password);

	public boolean signin();
}
