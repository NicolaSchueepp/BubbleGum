package ch.bbcag.bubblegum.websocket;

import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import ch.bbcag.bubblegum.service.IMessageService;
import ch.bbcag.bubblegum.service.message.JsonRequestMessage;

@ServerEndpoint("/chatService")
public class MessageSocket {

	@Inject
	private IMessageService messageService;
	
	
    @OnMessage
    public void onMessage(String message, Session session) {
    	messageService.spreadMessage(JsonRequestMessage.fromJson(message));
    }

    @OnOpen
    public void onOpen(Session session) {
    	messageService.registerClinet(session);
    }

    @OnClose
    public void onClose(Session session) {
    	messageService.removeClient(session);
    }
	  
}
