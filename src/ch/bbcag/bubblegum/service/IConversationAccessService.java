package ch.bbcag.bubblegum.service;

import javax.inject.Inject;

import ch.bbcag.bubblegum.bean.SessionBean;
import ch.bbcag.bubblegum.dao.IConversationAccessKeyDao;
import ch.bbcag.bubblegum.model.ConversationAccessKey;

public interface IConversationAccessService {

	public String getKeyHashForChat(long chatId);
	
	public boolean isValid(String hash, long chatId);
	
	public ConversationAccessKey getAccessKey(String hash);
}
