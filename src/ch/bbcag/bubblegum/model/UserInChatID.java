package ch.bbcag.bubblegum.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserInChatID implements Serializable {
	private static final long serialVersionUID = -8975038369142388883L;

	@Column(name = "user_id", updatable = false, insertable = false)
	private Long userId;

	@Column(name = "chat_id", updatable = false, insertable = false)
	private Long chatId;

	public UserInChatID() {
	}

	public UserInChatID(Long userId, Long chatId) {
		this.userId = userId;
		this.chatId = chatId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof UserInChatID))
			return false;
		UserInChatID that = (UserInChatID) o;
		return Objects.equals(getUserId(), that.getUserId()) && Objects.equals(getChatId(), that.getChatId());
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getChatId() {
		return chatId;
	}

	public void setChatId(Long chatId) {
		this.chatId = chatId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getUserId(), getChatId());
	}
}