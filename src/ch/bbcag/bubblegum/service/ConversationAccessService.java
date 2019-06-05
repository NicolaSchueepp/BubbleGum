package ch.bbcag.bubblegum.service;

import java.util.UUID;

import javax.inject.Inject;

import ch.bbcag.bubblegum.bean.SessionBean;
import ch.bbcag.bubblegum.dao.IConversationAccessKeyDao;
import ch.bbcag.bubblegum.model.ConversationAccessKey;

public class ConversationAccessService implements IConversationAccessService{

	@Inject
	private IConversationAccessKeyDao conversationAccessKeyDao;
	
	@Inject
	private SessionBean sessionBean;
	
	public String getKeyHashForChat(long chatId) {
		long userId = sessionBean.getUserID();
		ConversationAccessKey accessKey = new ConversationAccessKey();
		accessKey.setChatId(chatId);
		accessKey.setUserId(userId);
		accessKey.setCrationDate(System.currentTimeMillis());
		accessKey.setHash(UUID.randomUUID().toString());
		
		if(conversationAccessKeyDao.getByChatAnUser(userId, chatId) != null) {
			conversationAccessKeyDao.delete(conversationAccessKeyDao.getByChatAnUser(userId, chatId));
		}
		
		conversationAccessKeyDao.create(accessKey);
		return accessKey.getHash();
	}

	@Override
	public boolean isValid(String hash) {
		ConversationAccessKey accessKey;
		if((accessKey = conversationAccessKeyDao.getByHash(hash))!=null) {
			return (accessKey.getCrationDate() + 1800000) > System.currentTimeMillis();
		}
		return false;
	}

	@Override
	public ConversationAccessKey getAccessKey(String hash) {
		return conversationAccessKeyDao.getByHash(hash);
	}
}
