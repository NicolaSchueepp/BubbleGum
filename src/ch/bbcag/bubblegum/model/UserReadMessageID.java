package ch.bbcag.bubblegum.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserReadMessageID implements Serializable{

	private static final long serialVersionUID = 1123456788765L;

	@Column(name = "user_id", updatable = false, insertable = false)
	private Long userId;

	@Column(name = "message_id", updatable = false, insertable = false)
	private Long messageId;
	
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

	@Override
	public int hashCode() {
		return Objects.hash(getUserId(), getMessageId());
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof UserReadMessageID))
			return false;
		UserReadMessageID that = (UserReadMessageID) o;
		return Objects.equals(getUserId(), that.getUserId()) && Objects.equals(getMessageId(), that.getMessageId());
	}
}
