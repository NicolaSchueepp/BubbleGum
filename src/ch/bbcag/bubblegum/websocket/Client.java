package ch.bbcag.bubblegum.websocket;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.websocket.Session;

import com.sun.org.apache.regexp.internal.recompile;

import ch.bbcag.bubblegum.model.ConversationAccessKey;
import ch.bbcag.bubblegum.model.Message;
import ch.bbcag.bubblegum.service.IConversationAccessService;

public class Client {
	
	private Session session;
	private ConversationAccessKey accessKey;
	
	public Client(Session session, ConversationAccessKey accessKey) {
		this.session = session;
		this.accessKey = accessKey;
	}

	public void sendMessage(Message message) {
		JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
		objectBuilder.add("sendAt", message.getSendAt());
		objectBuilder.add("text", message.getText());
		objectBuilder.add("sendFrom", message.getUserId());
		
		JsonObject jsonObject = objectBuilder.build();
		String jsonString = null;
		try(Writer writer = new StringWriter()) {
		    Json.createWriter(writer).write(jsonObject);
		    jsonString = writer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		session.getAsyncRemote().sendText(jsonString);
	}

	public String getKey() {
		return accessKey.getHash();
	}

}
