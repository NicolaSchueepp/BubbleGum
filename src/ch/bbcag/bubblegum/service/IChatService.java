package ch.bbcag.bubblegum.service;

import java.util.List;

import ch.bbcag.bubblegum.model.Chat;

public interface IChatService {

	public List<Chat> getAllChats();

	public List<Chat> searchChatByName(String name);
}
