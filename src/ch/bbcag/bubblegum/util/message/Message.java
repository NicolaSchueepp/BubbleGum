package ch.bbcag.bubblegum.util.message;

public class Message {

	private String text;
	private MessageStyle level;
	
	public Message(MessageStyle level, String text) {
		this.text = text;
		this.level = level;
	}
	
	@Override
	public String toString() {
		return level.insert(text);
	}
}
