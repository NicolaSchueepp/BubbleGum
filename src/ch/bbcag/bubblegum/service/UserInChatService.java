package ch.bbcag.bubblegum.service;

import java.util.List;

import javax.inject.Inject;

import ch.bbcag.bubblegum.bean.SessionBean;
import ch.bbcag.bubblegum.dao.IUserInChatDao;
import ch.bbcag.bubblegum.model.UserInChat;

public class UserInChatService implements IUserInChatService {

	@Inject
	private IUserInChatDao userInChatDao;

	@Inject
	private SessionBean sessionBean;

	@Override
	public List<UserInChat> getAllChats() {
		List<UserInChat> chats = userInChatDao.getPersonalChats(sessionBean.getUserID());
		return chats;
	}

	@Override
	public List<UserInChat> getUsers(Long chatId) {
		return userInChatDao.getByChatUserId(chatId);
	}
}
