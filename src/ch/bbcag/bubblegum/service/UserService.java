package ch.bbcag.bubblegum.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import ch.bbcag.bubblegum.bean.RegisterBean;
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

	@Inject
	private RegisterBean registerBean;

	@Override
	public boolean login(String email, String password) {
		if (email != null && !email.isEmpty() && password != null && !password.isEmpty()) {
			User userSaved = userDao.getUserByEmail(email);
			if (userSaved != null && Arrays.equals(Util.encode(password).getBytes(),userSaved.getPassword().getBytes())) {
				sessionBean.setUserID(userSaved.getId());
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
				user.setStatus("Hey there! I am chewing a Bubble!");
				userDao.create(user);
				return true;
			} else {
				msgArray.addMessage(new Message(MessageStyle.error, "Email wurde schon verwendet!"));
				registerBean.setEmail("");
			}
		} else {
			msgArray.addMessage(new Message(MessageStyle.error, "Ungültige Eingabe!"));
		}
		return false;
	}

	@Override
	public List<User> searchUsersByName(String name) {
		List<User> users = new ArrayList<User>();
		if (name != null && !name.isEmpty()) {
			users = userDao.searchUserByName(name);
		}
		return users;
	}

	@Override
	public User getById(long id) {
		return userDao.getById(id);
	}

	@Override
	public boolean update(User user) {
		if (user != null) {
			userDao.updateUser(user);
			msgArray.addMessage(new Message(MessageStyle.Info, "Änderungen wurden erfolgreich gespeichert!"));
			return true;
		}
		msgArray.addMessage(new Message(MessageStyle.Info, "Etwas lief schief, kontaktieren Sie den Support!"));
		return false;
	}
}
