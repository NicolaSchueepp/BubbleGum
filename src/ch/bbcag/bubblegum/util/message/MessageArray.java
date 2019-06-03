package ch.bbcag.bubblegum.util.message;

import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class MessageArray {

	
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
