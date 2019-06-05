package ch.bbcag.bubblegum.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "Chat")
public class Chat implements Serializable {

	private static final long serialVersionUID = 2140513847887688809L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "is_bubble")
	private boolean isBubble;

	@Column(name = "name")
	private String name;
	
	@OneToMany
	@JoinColumn(name = "chat_id", insertable=false, updatable=false )
	private List<Message> messages;
	
	public String getLastMessage() {
		return messages.get(0).getText();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isBubble() {
		return isBubble;
	}

	public void setBubble(boolean isBubble) {
		this.isBubble = isBubble;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
