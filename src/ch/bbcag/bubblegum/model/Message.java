package ch.bbcag.bubblegum.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

@Entity
public class Message implements Serializable, Comparable<Message> {

	private static final long serialVersionUID = 2140513847887688809L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "text")
	private String text;

	@Column(name = "sent_at")
	private Long sentAt;

	@Column(name = "sent_by_user")
	private Long userId;

	@Column(name = "sent_in_chat")
	private Long chatId;

	@ManyToOne
	@JoinColumn(name = "sent_by_user", insertable = false, updatable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "sent_in_chat", insertable = false, updatable = false)
	private Chat chat;

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

	public Long getSentAt() {
		return sentAt;
	}

	@PrePersist
	public void setSendAt() {
		this.sentAt = System.currentTimeMillis();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return user == null ? "-" : user.getName();
	}

	public Long getChatId() {
		return chatId;
	}

	public void setChatId(Long chatId) {
		this.chatId = chatId;
	}

	@Override
	public int compareTo(Message o) {
		return (int) (o.sentAt - this.sentAt);
	}

}
