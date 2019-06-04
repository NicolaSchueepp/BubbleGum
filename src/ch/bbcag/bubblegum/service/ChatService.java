package ch.bbcag.bubblegum.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ch.bbcag.bubblegum.dao.IChatDao;
import ch.bbcag.bubblegum.model.Chat;
import ch.bbcag.bubblegum.util.message.Message;
import ch.bbcag.bubblegum.util.message.MessageArray;
import ch.bbcag.bubblegum.util.message.MessageStyle;

public class ChatService implements IChatService {

	@Inject
	private MessageArray msgArray;

	@Inject
	private IChatDao chatDao;

	@Override
	public List<Chat> searchAllChats() {
		List<Chat> chats = chatDao.searchAllChats();
		if (chats.isEmpty()) {
			msgArray.addMessage(new Message(MessageStyle.Warning, "Keine Chats mit diesem Namen gefunden"));
		}
		return chats;
	}

	@Override
	public List<Chat> searchChatByName(String name) {
		List<Chat> chats = new ArrayList<Chat>();
		if (name != null && !name.isEmpty()) {
			chats = chatDao.searchChatByName(name);
		}
		if (chats.isEmpty()) {
			msgArray.addMessage(new Message(MessageStyle.Warning, "Keine Chats mit diesem Namen gefunden"));
		}
		return chats;
	}

}
