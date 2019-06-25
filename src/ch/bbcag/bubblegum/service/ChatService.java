package ch.bbcag.bubblegum.service;

import javax.inject.Inject;

import ch.bbcag.bubblegum.bean.SessionBean;
import ch.bbcag.bubblegum.dao.IChatDao;
import ch.bbcag.bubblegum.dao.IUserInChatDao;
import ch.bbcag.bubblegum.model.Chat;
import ch.bbcag.bubblegum.model.User;
import ch.bbcag.bubblegum.model.UserInChat;
import ch.bbcag.bubblegum.util.message.Message;
import ch.bbcag.bubblegum.util.message.MessageArray;
import ch.bbcag.bubblegum.util.message.MessageStyle;

public class ChatService implements IChatService {

	@Inject
	private MessageArray msgArray;

	@Inject
	private IChatDao chatDao;
	
	@Inject
	private IUserInChatDao userInChatDao;

	@Inject
	private SessionBean sessionBean;
	
	@Inject
	private IUserService userService;
	
	@Inject
	private IUserInChatService userInChatService;
	
	@Override
	public Long createBubble(String name) {
		Chat chat = new Chat();
		chat.setBubble(true);
		chat.setName(name);
		chatDao.create(chat);
		
		userInChatService.addUser(chat.getId(),sessionBean.getUserID(),true);
		return chat.getId();
	}
	
	@Override
	public Long getQuickChatId(long userId) {
		if(userId == sessionBean.getUserID()) {
			msgArray.addMessage(new Message(MessageStyle.error,"Du kannst keinen Quick Chat mit dir selber öffnen"));
			return 0l;
		}
		
		if(chatDao.getQuickChatByMembers(sessionBean.getUserID(), userId) != null){
			return chatDao.getQuickChatByMembers(sessionBean.getUserID(), userId).getId();
		}
		Chat chat = new Chat();
		chat.setBubble(false);
		chat.setName("Quick-Chat");
		chatDao.create(chat);
		
		userInChatService.addUser(chat.getId(),sessionBean.getUserID(),false);
		userInChatService.addUser(chat.getId(),userId,false);
		
		return chat.getId();
	}


	
	@Override
	public String getChatName(long chatId) {
		String userName = "unnown";
		Chat chat;
		if(!(chat = chatDao.getById(chatId)).getName().equals("Quick-Chat")) {
			return chat.getName();
		}
		for (UserInChat u : userInChatDao.getByChatId(chatId)) {
			if(!u.getUser().getEmail().equals(userService.getById(sessionBean.getUserID()).getEmail())){
				userName = u.getUser().getName();
				break;
			}
		}
		return "Quick-Chat mit " + userName;
	}

	@Override
	public Chat getChat(long id) {
		return chatDao.getById(id);
	}

	@Override
	public boolean isAdmin(Long chatId) {
		return userInChatDao.getByUserIdAndChatId(sessionBean.getUserID(), chatId).isAdmin();
	}
	
}
