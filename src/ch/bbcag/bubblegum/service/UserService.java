package ch.bbcag.bubblegum.service;

import javax.inject.Inject;

import ch.bbcag.bubblegum.bean.HelperBean;
import ch.bbcag.bubblegum.bean.SessionBean;
import ch.bbcag.bubblegum.dao.IUserDao;
import ch.bbcag.bubblegum.model.User;
import ch.bbcag.bubblegum.util.Util;
import ch.bbcag.bubblegum.util.message.Message;
import ch.bbcag.bubblegum.util.message.MessageArray;
import ch.bbcag.bubblegum.util.message.MessageStyle;

public class UserService implements IUserService {

	@Inject
	private MessageArray msgArray;

	@Inject
	private IUserDao userDao;

	@Inject
	private SessionBean sessionBean;

	@Override
	public boolean login(String email, String password) {
		if (email != null && !email.isEmpty() && password != null && !password.isEmpty()) {
			User userSaved = userDao.getUserByEmail(email);
			if (userSaved != null && Util.encode(password).equals(userSaved.getPassword())) {
				sessionBean.setUserID(userSaved.getId());
				sessionBean.addLogedIn();
				return true;
			}
		}
		msgArray.addMessage(new Message(MessageStyle.error, "Email/Passwort ist falsch!"));
		return false;
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
				return true;
			} else {
				msgArray.addMessage(new Message(MessageStyle.error, "Email wurde schon verwendet!"));
			}
		} else {
			msgArray.addMessage(new Message(MessageStyle.error, "Ungültige Eingabe!"));
		}
		return false;
	}
}
