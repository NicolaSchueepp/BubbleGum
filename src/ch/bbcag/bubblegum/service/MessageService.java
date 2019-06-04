package ch.bbcag.bubblegum.service;

import javax.inject.Inject;
import javax.websocket.Session;

import ch.bbcag.bubblegum.dao.IConversationAccessKeyDao;
import ch.bbcag.bubblegum.model.ConversationAccessKey;
import ch.bbcag.bubblegum.service.message.Client;
import ch.bbcag.bubblegum.service.message.ClientPool;
import ch.bbcag.bubblegum.service.message.JsonRequestMessage;
import ch.bbcag.bubblegum.service.message.JsonResponseMessage;

public class MessageService implements IMessageService{


	@Inject
	private ClientPool clientPool;
	
	@Inject
	private IConversationAccessKeyDao conversationAccessKeyDao;
	
	@Override
	public void registerClinet(Session session) {
		String hash = session.getRequestParameterMap().get("hash").get(0);
		String requestedChat = session.getRequestParameterMap().get("chatId").get(0);
		
		if(isValid(hash, Long.valueOf(requestedChat))) {
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
		if(isValid(message.getHash(),message.getChatId())){
			for(Client client : clientPool.getByChatId(message.getChatId())) {
				if(isValid(client.getHash(), message.getChatId())) {
					client.send(new JsonResponseMessage(message.getText(), String.valueOf(client.getAccessKey().getUserId())));
				}
			}
			System.out.println("MESSAGE SHARED WITH " + clientPool.getByChatId(message.getChatId()).size() + " CLIENTS -------------------------------------------------------------------");
		}else {
			System.out.println("MESSAGE INVALID -------------------------------------------------------------------");
		}
	}
	
	private boolean isValid(String hash, long chatId) {
		ConversationAccessKey accessKey = conversationAccessKeyDao.getByHash(hash);
		return accessKey != null && chatId == accessKey.getChatId() && (accessKey.getCrationDate() + 1800000) > System.currentTimeMillis();
	}
}
