package ch.bbcag.bubblegum.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.bbcag.bubblegum.service.IUserService;

@Named
@SessionScoped
public class RegisterBean implements Serializable {
	private static final long serialVersionUID = -8845855312932960905L;

	private String name = "";
	private String email = "";
	private String password = "";

	@Inject
	private transient IUserService userService;

	@Inject
	private LoginBean loginBean;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String register() {
		if (userService.register(name, email, password)) {
			resetFields();
			loginBean.setEmail(email);
			return "login";
		}
		password = "";
		return "register";
	}
	
	private void resetFields() {
		this.email = "";
		this.name = "";
		this.password = "";
	}
}
