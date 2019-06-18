package ch.bbcag.bubblegum.service;

import java.util.List;

import javax.inject.Inject;

import ch.bbcag.bubblegum.bean.SessionBean;
import ch.bbcag.bubblegum.dao.IUserInChatDao;
import ch.bbcag.bubblegum.model.Chat;
import ch.bbcag.bubblegum.model.User;
import ch.bbcag.bubblegum.model.UserInChat;
import ch.bbcag.bubblegum.util.message.Message;
import ch.bbcag.bubblegum.util.message.MessageArray;
import ch.bbcag.bubblegum.util.message.MessageStyle;

public class UserInChatService implements IUserInChatService {

	@Inject
	private IUserInChatDao userInChatDao;

	@Inject
	private SessionBean sessionBean;
	
	@Inject
	private MessageArray messageArray;

	@Override
	public List<UserInChat> getAllChats() {
		List<UserInChat> chats = userInChatDao.getPersonalChats(sessionBean.getUserID());
		return chats;
	}

	@Override
	public List<UserInChat> getUsers(Long chatId) {
		return userInChatDao.getByChatUserId(chatId);
	}
	

	@Override
	public boolean addUser(long chatId, long userId, boolean admin) {
		if (userInChatDao.getByUserIdAndChatId(userId, chatId) != null) {
			messageArray.addMessage(new Message(MessageStyle.Warning,"Dieser Nutzer ist bereits im Chat"));
			return false;
		}
		User user = new User();
		user.setId(userId);
		Chat chat = new Chat();
		chat.setId(chatId);
		UserInChat userInChat = new UserInChat();
		userInChat.setAdmin(admin);
		userInChat.setChat(chat);
		userInChat.setUser(user);
		userInChatDao.create(userInChat);
		return true;
	}
}
