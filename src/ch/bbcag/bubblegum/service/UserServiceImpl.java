package ch.bbcag.bubblegum.service;

import javax.inject.Inject;

import ch.bbcag.bubblegum.dao.IUserDao;
import ch.bbcag.bubblegum.model.User;
import ch.bbcag.bubblegum.util.Util;

public class UserServiceImpl implements UserService {

	@Inject
	private IUserDao userDao;

	@Override
	public boolean signin() {
		return true;
	}

	@Override
	public boolean register(String name, String email, String password) {
		if (name != null && !name.isEmpty() && email != null && !email.isEmpty() && password != null
				&& !password.isEmpty()) {
			User user = new User();
			user.setEmail(email);
			user.setName(name);
			user.setPassword(Util.encode(password));
			userDao.create(user);
		}
		return false;
	}
}
