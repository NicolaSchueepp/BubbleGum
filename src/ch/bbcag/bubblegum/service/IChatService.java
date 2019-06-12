package ch.bbcag.bubblegum.service;

public interface IChatService {
	
	public Long getQuickChatId(long userId);
	
	public String getChatName(long chatId);
}
