package ch.bbcag.bubblegum.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ch.bbcag.bubblegum.dao.IChatDao;
import ch.bbcag.bubblegum.model.Chat;
import ch.bbcag.bubblegum.util.message.MessageArray;

public class ChatService implements IChatService {

	@Inject
	private MessageArray msgArray;

	@Inject
	private IChatDao chatDao;

	@Override
	public List<Chat> getAllChats() {
		List<Chat> chats = chatDao.getAllChats();
		return chats;
	}

	@Override
	public List<Chat> searchChatByName(String name) {
		List<Chat> chats = new ArrayList<Chat>();
		if (name != null && !name.isEmpty()) {
			chats = chatDao.searchChatByName(name);
		}
		return chats;
	}
}
