package ch.bbcag.bubblegum.service.message;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class JsonResponseMessage {

	private String text;
	private String from;
	
	public JsonResponseMessage(String text, String from) {
		this.text = text;
		this.from = from;
	}

	public String getFrom() {
		return from;
	}
	
	public String getText() {
		return text;
	}
	
	public String toJson() {
		JsonObjectBuilder objectBuilder = Json.createObjectBuilder()
				  .add("text", text)
				  .add("from",from);
		JsonObject jsonObject = objectBuilder.build();
		String jsonString = "";
		try(Writer writer = new StringWriter()) {
		    Json.createWriter(writer).write(jsonObject);
		    jsonString = writer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonString;
	}
}
