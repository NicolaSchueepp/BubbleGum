package ch.bbcag.bubblegum.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Invite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "is_accepted")
	private boolean isAccepted;
	
	@Column(name = "sent_by_user")
	private long senderId;
	
	@Column(name = "for_chat")
	private long chatId;
	
	@Column(name = "invited_user")
	private long invitedtId;

	@ManyToOne
	@JoinColumn(name = "for_chat", insertable = false, updatable = false)
	private Chat chat;
	
	@ManyToOne
	@JoinColumn(name = "sent_by_user", insertable = false, updatable = false)
	private User sender;

	public User getSender() {
		return sender;
	}
	
	public void setSender(User sender) {
		this.sender = sender;
	}
	
	public Chat getChat() {
		return chat;
	}
	
	public void setChat(Chat chat) {
		this.chat = chat;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isAccepted() {
		return isAccepted;
	}

	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	public long getSenderId() {
		return senderId;
	}

	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}

	public long getChatId() {
		return chatId;
	}

	public void setChatId(long chatId) {
		this.chatId = chatId;
	}

	public long getInvitedtId() {
		return invitedtId;
	}

	public void setInvitedtId(long invitedtId) {
		this.invitedtId = invitedtId;
	}

}
