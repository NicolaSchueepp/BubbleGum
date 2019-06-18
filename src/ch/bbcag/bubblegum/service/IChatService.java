package ch.bbcag.bubblegum.service;

import ch.bbcag.bubblegum.model.Chat;

public interface IChatService {
	
	public Long getQuickChatId(long userId);
	
	public String getChatName(long chatId);
	
	public Chat getChat(long id);

	public Long createBubble(String name);
}
