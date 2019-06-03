package ch.bbcag.bubblegum.bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.bbcag.bubblegum.service.IUserService;

@Named
@RequestScoped
public class LoginBean implements Serializable {
	private static final long serialVersionUID = -7585723027391394711L;
	
	private String email = "";
	private String password = "";

	@Inject
	private IUserService userService;

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

	public String login() {
		if (userService.login(email, password))
			return "overview";
		return "login";
	}
}
