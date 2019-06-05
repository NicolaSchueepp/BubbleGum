package ch.bbcag.bubblegum.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "User_in_chat")
@Entity()
public class UserInChat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "chat_id")
	private Long chatId;
	
	@Column(name = "is_admin")
	private boolean isAdmin;
}
