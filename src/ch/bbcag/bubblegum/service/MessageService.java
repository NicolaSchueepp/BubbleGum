package ch.bbcag.bubblegum.service;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.activation.MailcapCommandMap;
import javax.inject.Inject;
import javax.websocket.Session;

import com.sun.mail.handlers.message_rfc822;

import ch.bbcag.bubblegum.bean.SessionBean;
import ch.bbcag.bubblegum.dao.IChatDao;
import ch.bbcag.bubblegum.dao.IConversationAccessKeyDao;
import ch.bbcag.bubblegum.dao.IMessageDao;
import ch.bbcag.bubblegum.dao.IUserInChatDao;
import ch.bbcag.bubblegum.dao.IUserReadMessageDao;
import ch.bbcag.bubblegum.dao.UserReadMessageDao;
import ch.bbcag.bubblegum.model.Chat;
import ch.bbcag.bubblegum.model.ConversationAccessKey;
import ch.bbcag.bubblegum.model.Message;
import ch.bbcag.bubblegum.model.UserInChat;
import ch.bbcag.bubblegum.model.UserReadMessage;
import ch.bbcag.bubblegum.service.message.Client;
import ch.bbcag.bubblegum.service.message.ClientPool;
import ch.bbcag.bubblegum.service.message.JsonRequestMessage;
import ch.bbcag.bubblegum.service.message.JsonResponseMessage;
import ch.bbcag.bubblegum.util.message.MessageArray;
import ch.bbcag.bubblegum.util.message.MessageStyle;

public class MessageService implements IMessageService{


	@Inject
	private ClientPool clientPool;
	
	@Inject
	private IConversationAccessKeyDao conversationAccessKeyDao;
	
	@Inject
	private IMessageDao messageDao;
	
	@Inject
	private MessageArray messageArray;
	
	@Inject
	private UserService userService;
	
	@Inject
	private IUserInChatDao userInChatDao;
	
	@Inject
	private IConversationAccessService conversationAccessService;
	
	@Inject
	private IUserReadMessageDao userReadMessageDao;
	
	@Inject
	private SessionBean sessionBean;
	
	@Override
	public void registerClinet(Session session) {
		String hash = session.getRequestParameterMap().get("hash").get(0);
		String requestedChat = session.getRequestParameterMap().get("chatId").get(0);
		
		if(conversationAccessService.isValid(hash, Long.valueOf(requestedChat))) {
			ConversationAccessKey accessKey = conversationAccessKeyDao.getByHash(hash);
			Client client = new Client(session, hash, accessKey);
			clientPool.add(client);
		}
	}

	@Override
	public void removeClient(Session session) {
		String hash = session.getRequestParameterMap().get("hash").get(0);
		if(clientPool.getByHash(hash) != null)
			clientPool.remove(clientPool.getByHash(hash));
	}

	@Override
	public void spreadMessage(JsonRequestMessage message) {
		Client sender = clientPool.getByHash(message.getHash());
		
		if(conversationAccessService.isValid(message.getHash(),message.getChatId()) && sender != null){
			Message dbMessage = new Message();
			dbMessage.setChatId(message.getChatId());
			dbMessage.setSendAt();
			dbMessage.setText(message.getText());
			dbMessage.setUserId(sender.getAccessKey().getUserId());
			messageDao.create(dbMessage);
			
			UserReadMessage userReadMessage = new UserReadMessage();
			userReadMessage.setMessageId(dbMessage.getId());
			for(Client client : clientPool.getByChatId(message.getChatId())) {
				if(conversationAccessService.isValid(client.getHash(), message.getChatId())) {
					userReadMessage.setUserId(client.getAccessKey().getUserId());
					userReadMessageDao.create(userReadMessage);
					
					client.send(new JsonResponseMessage(message.getText(), userService.getById(sender.getAccessKey().getUserId()).getName()));
				}
			}
		}
	}
	


	@Override
	public List<Message> getMessages(long chatId, String hash) {
		if(conversationAccessService.isValid(hash, chatId)) {
			List<Message> messages = messageDao.getByChatId(chatId);
			UserReadMessage userReadMessage = new UserReadMessage();
			userReadMessage.setUserId(sessionBean.getUserID());
			for (Message message : messages) {
				if(userReadMessageDao.getByUserIdAndMessageId(sessionBean.getUserID(), message.getId()) == null) {
					userReadMessage.setMessageId(message.getId());
					userReadMessageDao.create(userReadMessage);
				}
			}
			return messageDao.getByChatId(chatId);
		}
		return null;
	}

	@Override
	public ArrayList<Entry<Message, Integer>> getNewQuickMessages() {
		ArrayList<Entry<Message, Integer>> messages = new ArrayList<Entry<Message, Integer>>();
		for(UserInChat u : userInChatDao.getByUserId(sessionBean.getUserID())) {
			messages.addAll(groupUnreadMesagesByChat(messageDao.getByChatId(u.getChat().getId()).stream().filter((c) -> !c.getChat().isBubble()).collect(Collectors.toList())));
		}
		return messages;
	}

	@Override
	public ArrayList<Entry<Message, Integer>> getNewBubbleMessages() {
		ArrayList<Entry<Message, Integer>> messages = new ArrayList<Entry<Message, Integer>>();
		for(UserInChat u : userInChatDao.getByUserId(sessionBean.getUserID())) {
			messages.addAll(groupUnreadMesagesByChat(messageDao.getByChatId(u.getChat().getId()).stream().filter((c) -> c.getChat().isBubble()).collect(Collectors.toList())));
		}
		return messages;
	}

	private ArrayList<Entry<Message, Integer>> groupUnreadMesagesByChat(List<Message> messages){
		Map<Long,Entry<Message, Integer>> grouptMessages = new HashMap<Long, Map.Entry<Message,Integer>>();
		for (Message message : messages) {
			if(userReadMessageDao.getByUserIdAndMessageId(sessionBean.getUserID(), message.getId()) == null) {
				if(grouptMessages.containsKey(message.getChatId())) {
					grouptMessages.get(message.getChatId()).setValue(grouptMessages.get(message.getChatId()).getValue()+1);
				} else {
					grouptMessages.put(message.getChatId(), new AbstractMap.SimpleEntry<Message,Integer>(message.getChat().getLastMessage(),1));
				}
			}
		}
		return new ArrayList<Entry<Message, Integer>>(grouptMessages.values());
	}

	@Override
	public void deleteMessages(long chatId) {
		if(userInChatDao.getByUserIdAndChatId(sessionBean.getUserID(), chatId).isAdmin() || !userInChatDao.getByUserIdAndChatId(sessionBean.getUserID(), chatId).getChat().isBubble()) {
			messageDao.deleteByChat(chatId);
			messageArray.addMessage(new ch.bbcag.bubblegum.util.message.Message(MessageStyle.Info, "Chat Verlauf erfolgreich gelöscht"));
		}else {
			messageArray.addMessage(new ch.bbcag.bubblegum.util.message.Message(MessageStyle.Info, "Du kannst den Verlauf nicht löschen"));
		}
		
	}
}
