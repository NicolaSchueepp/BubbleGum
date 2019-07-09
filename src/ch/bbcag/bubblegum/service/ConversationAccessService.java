package ch.bbcag.bubblegum.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import ch.bbcag.bubblegum.bean.SessionBean;
import ch.bbcag.bubblegum.dao.IConversationAccessKeyDao;
import ch.bbcag.bubblegum.dao.IUserInChatDao;
import ch.bbcag.bubblegum.model.ConversationAccessKey;
import ch.bbcag.bubblegum.util.LogInitializer;
import ch.bbcag.bubblegum.util.Util;
import ch.bbcag.bubblegum.util.message.Message;
import ch.bbcag.bubblegum.util.message.MessageArray;
import ch.bbcag.bubblegum.util.message.MessageStyle;

public class ConversationAccessService implements IConversationAccessService{

	@Inject
	private IConversationAccessKeyDao conversationAccessKeyDao;
	
	@Inject
	private SessionBean sessionBean;
	
	@Inject
	private IUserInChatDao userInChatDao;
	
	@Inject
	private MessageArray messageArray;
	
	private final Logger LOGGER = new LogInitializer(getClass().getName()).initConsole().getLogger();
	
	@Override
	public String getKeyHashForChat(long chatId) {
		long userId = sessionBean.getUserID();
		
		if(userInChatDao.getByUserIdAndChatId(userId, chatId) == null) {
			LOGGER.log(Level.FINEST, "Unaotorized chat access by user " + sessionBean.getUserID() + " in chat " + chatId);
			messageArray.addMessage(new Message(MessageStyle.error, "Du hast keinen zugriff zu diesem Chat"));
			return null;
		}
		
		ConversationAccessKey accessKey = new ConversationAccessKey();
		accessKey.setChatId(chatId);
		accessKey.setUserId(userId);
		accessKey.setCrationDate(System.currentTimeMillis());
		accessKey.setHash(Util.generateKey(chatId+"-"+userId));
		
		if(conversationAccessKeyDao.getByChatAnUser(userId, chatId) != null) {
			conversationAccessKeyDao.delete(conversationAccessKeyDao.getByChatAnUser(userId, chatId));
		}
		conversationAccessKeyDao.create(accessKey);
		return accessKey.getHash();
	}

	@Override
	public ConversationAccessKey getAccessKey(String hash) {
		return conversationAccessKeyDao.getByHash(hash);
	}
	
	@Override
	public boolean isValid(String hash, long chatId) {
		ConversationAccessKey accessKey = conversationAccessKeyDao.getByHash(hash);
		if(accessKey != null && chatId == accessKey.getChatId()) {
			if(accessKey.getCrationDate() + 1800000 < System.currentTimeMillis()){
				conversationAccessKeyDao.delete(accessKey);
			} else {
				return true;
			}
		}
		return false;
	}
}
