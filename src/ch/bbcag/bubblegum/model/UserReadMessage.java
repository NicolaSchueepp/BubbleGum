package ch.bbcag.bubblegum.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "user_read_message")
@Entity()
@Embeddable
public class UserReadMessage {

	@EmbeddedId
	private UserReadMessageID userReadMessageID;
	
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "message_id")
	private Long messageId;

	public UserReadMessageID getUserReadMessageID() {
		return userReadMessageID;
	}

	public void setUserReadMessageID(UserReadMessageID userReadMessageID) {
		this.userReadMessageID = userReadMessageID;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}
	

}
