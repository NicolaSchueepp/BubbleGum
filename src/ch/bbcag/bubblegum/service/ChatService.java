package ch.bbcag.bubblegum.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ch.bbcag.bubblegum.bean.SessionBean;
import ch.bbcag.bubblegum.dao.IChatDao;
import ch.bbcag.bubblegum.dao.IUserInChatDao;
import ch.bbcag.bubblegum.dao.UserInChatDao;
import ch.bbcag.bubblegum.model.Chat;
import ch.bbcag.bubblegum.model.UserInChat;
import ch.bbcag.bubblegum.util.message.MessageArray;

public class ChatService implements IChatService {

	@Inject
	private MessageArray msgArray;

	@Inject
	private IChatDao chatDao;
	
	@Inject
	private IUserInChatDao userInChatDao;

	@Inject
	private SessionBean sessionBean;
	
	@Override
	public Long getQuickChatId(long userId) {
		if(chatDao.getQuickChatByMembers(sessionBean.getUserID(), userId) != null){
			return chatDao.getQuickChatByMembers(sessionBean.getUserID(), userId).getId();
		}
		Chat chat = new Chat();
		chat.setBubble(false);
		chat.setName("Quick-Chat");
		chat = chatDao.create(chat);
		
		UserInChat userInChat1 = new UserInChat();
		userInChat1.setAdmin(false);
		userInChat1.setChatId(chat.getId());
		userInChat1.setUserId(sessionBean.getUserID());
		userInChatDao.create(userInChat1);
		
		UserInChat userInChat2 = new UserInChat();
		userInChat2.setAdmin(false);
		userInChat2.setChatId(chat.getId());
		userInChat2.setUserId(userId);
		userInChatDao.create(userInChat2);
		
		return chat.getId();
	}
}
