package ch.bbcag.bubblegum.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "conversation_access_key")
@Entity
public class ConversationAccessKey implements Serializable{

	private static final long serialVersionUID = -5493025581887344670L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "userId")
	private long userId;
	
	@Column(name = "chatId")
	private Long chatId;
	
	@Column(name = "creation_date")
	private Long crationDate;
	
	@Column(name = "hash")
	private String hash;

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public long getChatId() {
		return chatId;
	}

	public void setChatId(Long chatId) {
		this.chatId = chatId;
	}

	public long getCrationDate() {
		return crationDate;
	}

	public void setCrationDate(Long crationDate) {
		this.crationDate = crationDate;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}
	
}
