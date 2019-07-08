package ch.bbcag.bubblegum.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "email_verification_key")
@Entity()
public class EmailVerificationKey {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id")
	private long userId;


	@Column(name = "hash")
	private String hash;

	@Column(name = "creation_date")
	private long creationDate;

	public long getCreationDate() {
		return creationDate;
	}

	public Long getId() {
		return id;
	}

	public String getHash() {
		return hash;
	}

	public long getUserId() {
		return userId;
	}

	public void setCreationDate(long creationDate) {
		this.creationDate = creationDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
