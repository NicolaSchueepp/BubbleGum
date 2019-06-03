package ch.bbcag.bubblegum.service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

public class UserServiceImpl implements UserService {

	@PersistenceUnit
	private EntityManagerFactory emf;
	
	@Override
	public boolean register() {
		return true;
	}

	@Override
	public boolean signin() {
		return true;
	}
}
