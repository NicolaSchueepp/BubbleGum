package ch.bbcag.bubblegum.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.inject.Inject;

import ch.bbcag.bubblegum.dao.UserDao;
import ch.bbcag.bubblegum.model.User;
import ch.bbcag.bubblegum.util.message.Message;
import ch.bbcag.bubblegum.util.message.MessageArray;
import ch.bbcag.bubblegum.util.message.MessageStyle;

public class SearchService implements ISearchService{

	@Inject
	private UserDao userDao;
	
	@Inject
	private MessageArray messageArray;
	
	@Override
	public List<User> getUsersByName(String name) {
		List<User> users = new ArrayList<User>();
		if(name != null && !name.isEmpty()) {
			users = userDao.searchUserByName(name);
		}
		if(users == null || users.isEmpty()) {
			messageArray.addMessage(new Message(MessageStyle.Warning, "Keine Nutzer mit diesem namen gefunden " + Arrays.toString(users.toArray())));
		}
		return users;
	}

}
