package ch.bbcag.bubblegum.service.message;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class JsonRequestMessage {

	private long chatId;
	private String hash;
	private String text;
	
	public long getChatId() {
		return chatId;
	}
	
	public String getHash() {
		return hash;
	}
	
	public String getText() {
		return text;
	}
	
	public static JsonRequestMessage fromJson(String jsonString) {
		JsonReader reader = Json.createReader(new StringReader(jsonString));
		JsonObject jsonObject = reader.readObject();
		JsonRequestMessage jsonRequestMessage = new JsonRequestMessage();
		jsonRequestMessage.chatId = Long.valueOf(jsonObject.getString("chatId"));
		jsonRequestMessage.hash = jsonObject.getString("hash");
		jsonRequestMessage.text = jsonObject.getString("text");
		return jsonRequestMessage;
	}
	
}
