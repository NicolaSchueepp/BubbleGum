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
	
	
	@Override
	public void registerClinet(Session session) {
		String hash = session.getRequestParameterMap().get("hash").get(0);
		String requestedChat = session.getRequestParameterMap().get("chatId").get(0);
		
		if(isValid(hash, Long.valueOf(requestedChat))) {
			ConversationAccessKey accessKey = conversationAccessKeyDao.getByHash(hash);
			Client client = new Client(session, hash, accessKey);
			clientPool.add(client);
			System.out.println("NEW CLIENT ADDED -------------------------------------------------------------------");
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
		
		if(isValid(message.getHash(),message.getChatId())){
			Message dbMessage = new Message();
			dbMessage.setChatId(message.getChatId());
			dbMessage.setSendAt();
			dbMessage.setText(message.getText());
			dbMessage.setUserId(sender.getAccessKey().getUserId());
			messageDao.create(dbMessage);
			
			for(Client client : clientPool.getByChatId(message.getChatId())) {
				if(isValid(client.getHash(), message.getChatId())) {
					client.send(new JsonResponseMessage(message.getText(), String.valueOf(sender.getAccessKey().getUserId())));
				}
			}
			System.out.println("MESSAGE SHARED WITH " + clientPool.getByChatId(message.getChatId()).size() + " CLIENTS -------------------------------------------------------------------");
		}else {
			System.out.println("MESSAGE INVALID -------------------------------------------------------------------");
		}
	}
	
	private boolean isValid(String hash, long chatId) {
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

	@Override
	public List<Message> getByChatId(long chatId) {
		return messageDao.getByChatId(chatId);
	}
}
