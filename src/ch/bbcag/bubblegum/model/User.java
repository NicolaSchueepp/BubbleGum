package ch.bbcag.bubblegum.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "User")
public class User implements Serializable {

	private static final long serialVersionUID = 2140513847887688809L;

	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "status")
	private String status;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

}
