package ch.bbcag.bubblegum.bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class RegisterBean implements Serializable {
	private static final long serialVersionUID = -8845855312932960905L;

	private String email = "";
	private String password = "";

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

	public String register() {
		return "signin";
	}
}
