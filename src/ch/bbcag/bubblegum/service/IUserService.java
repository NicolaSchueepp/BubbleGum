package ch.bbcag.bubblegum.service;

public interface IUserService {

	public boolean register(String name, String email, String password);

	public boolean login(String email, String password);
}
