package ch.bbcag.bubblegum.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
