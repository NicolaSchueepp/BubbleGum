package ch.bbcag.bubblegum.service;

import javax.inject.Inject;

import ch.bbcag.bubblegum.dao.IUserDao;
import ch.bbcag.bubblegum.model.User;
import ch.bbcag.bubblegum.util.Util;
import ch.bbcag.bubblegum.util.message.Message;
import ch.bbcag.bubblegum.util.message.MessageArray;
import ch.bbcag.bubblegum.util.message.MessageStyle;

public class UserServiceImpl implements UserService {

	@Inject
	private MessageArray msgArray;

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
			if (userDao.getUserByEmail(email) == null) {
				User user = new User();
				user.setEmail(email);
				user.setName(name);
				user.setPassword(Util.encode(password));
				user.setStatus("");
				userDao.create(user);
			} else {
				msgArray.addMessage(new Message(MessageStyle.error, "Email wurde schon verwendet!"));
			}
		} else {
			msgArray.addMessage(new Message(MessageStyle.error, "Ungültige Eingabe!"));
		}
		return false;
	}
}
