package ch.bbcag.bubblegum.service;

import java.util.List;

import javax.inject.Inject;
import javax.websocket.Session;

import ch.bbcag.bubblegum.dao.IConversationAccessKeyDao;
import ch.bbcag.bubblegum.dao.IMessageDao;
import ch.bbcag.bubblegum.model.ConversationAccessKey;
import ch.bbcag.bubblegum.model.Message;
import ch.bbcag.bubblegum.service.message.Client;
import ch.bbcag.bubblegum.service.message.ClientPool;
import ch.bbcag.bubblegum.service.message.JsonRequestMessage;
import ch.bbcag.bubblegum.service.message.JsonResponseMessage;

public class MessageService implements IMessageService{


	@Inject
	private ClientPool clientPool;
	
	@Inject
	private IConversationAccessKeyDao conversationAccessKeyDao;
	
	@Inject
	private IMessageDao messageDao;
	
	@Inject
	private UserService userService;
	
	@Inject
	private IConversationAccessService conversationAccessService;
	
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
			
			for(Client client : clientPool.getByChatId(message.getChatId())) {
				if(conversationAccessService.isValid(client.getHash(), message.getChatId())) {
					client.send(new JsonResponseMessage(message.getText(), userService.getById(sender.getAccessKey().getUserId()).getName()));
				}
			}
		}
	}
	


	@Override
	public List<Message> getMessages(long chatId, String hash) {
		if(conversationAccessService.isValid(hash, chatId)) {
			return messageDao.getByChatId(chatId);
		}
		return null;
	}
}
