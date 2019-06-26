package ch.bbcag.bubblegum.service;

import java.util.List;

import javax.inject.Inject;

import ch.bbcag.bubblegum.bean.SessionBean;
import ch.bbcag.bubblegum.dao.ConversationAccessKeyDao;
import ch.bbcag.bubblegum.dao.IChatDao;
import ch.bbcag.bubblegum.dao.IConversationAccessKeyDao;
import ch.bbcag.bubblegum.dao.IInviteDao;
import ch.bbcag.bubblegum.dao.IMessageDao;
import ch.bbcag.bubblegum.dao.IUserInChatDao;
import ch.bbcag.bubblegum.dao.IUserReadMessageDao;
import ch.bbcag.bubblegum.dao.UserReadMessageDao;
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
	private IInviteDao inviteDao;
	
	@Inject
	private IConversationAccessKeyDao conversationAccessKeyDao;
	
	@Inject
	private IChatDao chatDao;

	@Inject
	private MessageArray messageArray;

	@Override
	public List<UserInChat> getAllChats() {
		List<UserInChat> chats = userInChatDao.getByUserId(sessionBean.getUserID());
		return chats;
	}

	@Override
	public List<UserInChat> getUsers(Long chatId) {
		return userInChatDao.getByChatId(chatId);
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

	@Override
	public boolean removeUser(Long chatId, Long userId) {
		if(!userInChatDao.getByUserIdAndChatId(sessionBean.getUserID(), chatId).isAdmin()) {
			messageArray.addMessage(new Message(MessageStyle.error,"Du kannst keine nutzer entfernen"));
			return false;
		}
		userInChatDao.delete(userInChatDao.getByUserIdAndChatId(userId, chatId));
		conversationAccessKeyDao.delete(conversationAccessKeyDao.getByChatAnUser(userId, chatId));
		messageArray.addMessage(new Message(MessageStyle.Info,"Nutzer erfolgreich entfernt"));
		inviteDao.delete(inviteDao.getByUserAndChatId(userId, chatId));
		return true;
	}

	@Override
	public boolean removeSelf(Long chatId) {
		userInChatDao.delete(userInChatDao.getByUserIdAndChatId(sessionBean.getUserID(), chatId));
		if(!chatDao.getById(chatId).isBubble()) {
			UserInChat userInChat = userInChatDao.getByChatId(chatId).get(0);
			String userName = userInChat.getUser().getName();
			chatDao.delete(chatDao.getById(chatId));
			messageArray.addMessage(new Message(MessageStyle.Info,"Der Quick-Chat mit "+userName+" wurde gelöcht"));
		}else if(userInChatDao.getByChatId(chatId).size() == 0) {
			chatDao.delete(chatDao.getById(chatId));
			messageArray.addMessage(new Message(MessageStyle.Info,"Der Chat wurde gelöscht da du das einzige mitglied warst"));
		}else {
			messageArray.addMessage(new Message(MessageStyle.Info,"Du hast den Chat Verlassen"));
		}
		return true;
	}

	@Override
	public boolean promote(Long chatId, Long userId) {
		if(!userInChatDao.getByUserIdAndChatId(sessionBean.getUserID(), chatId).isAdmin()) {
			messageArray.addMessage(new Message(MessageStyle.error,"Du kannst keine Nutzer Promoten"));
			return false;
		}
		if(userInChatDao.getByUserIdAndChatId(userId, chatId).isAdmin()) {
			messageArray.addMessage(new Message(MessageStyle.error,"Dieser Nutzer wurde bereits Promoted"));
			return false;
		}
		messageArray.addMessage(new Message(MessageStyle.Info,"Nutzer erfolgreich Promoted"));
		UserInChat userInChat = userInChatDao.getByUserIdAndChatId(userId, chatId);
		userInChat.setAdmin(true);
		userInChatDao.update(userInChat);
		return true;
	}
}
