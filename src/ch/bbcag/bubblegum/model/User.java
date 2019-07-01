package ch.bbcag.bubblegum.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity(name = "User")
public class User implements Serializable {

	private static final long serialVersionUID = 2140513847887688809L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "status")
	private String status;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "name")
	private String name;
	
	@Column(name = "email_verified")
	private boolean EmailVerified;
	
	@ManyToMany
	private List<Chat> chats;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isEmailVerified() {
		return EmailVerified;
	}
	
	public void setEmailVerified(boolean emailVerified) {
		EmailVerified = emailVerified;
	}

}
