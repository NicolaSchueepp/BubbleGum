package ch.bbcag.bubblegum.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Message implements Serializable {

	private static final long serialVersionUID = 2140513847887688809L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "text")
	private String text;
	
	@Column(name = "sent_at")
	private Long sendAt;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "chat_id")
	private Long chatId;

	@ManyToOne
	@JoinColumn(name = "user_id", insertable=false, updatable=false )
	private User user;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getSendAt() {
		return sendAt;
	}

	public void setSendAt(Long sendAt) {
		this.sendAt = sendAt;
	}

	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return user.getName();
	}

	public Long getChatId() {
		return chatId;
	}

	public void setChatId(Long chatId) {
		this.chatId = chatId;
	}
	
	
}
