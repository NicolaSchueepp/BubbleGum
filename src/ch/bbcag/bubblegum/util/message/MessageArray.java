package ch.bbcag.bubblegum.util.message;

import java.io.Serializable;
import java.util.ArrayList;

import javax.enterprise.context.SessionScoped;

@SessionScoped
public class MessageArray implements Serializable{
	
	private static final long serialVersionUID = -4732122833002795431L;
	
	private ArrayList<Message> messages;
	
	public MessageArray() {
		messages = new ArrayList<Message>();
	}
	
	public void addMessage(Message message) {
		messages.add(message);
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (Message message : messages) {
			stringBuilder.append(message.toString());
		}
		return stringBuilder.toString();
	}
	
	public void clear() {
		messages.clear();
	}
}
