package ch.bbcag.bubblegum.bean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.bbcag.bubblegum.model.User;
import ch.bbcag.bubblegum.service.IMailService;
import ch.bbcag.bubblegum.service.UserService;
import ch.bbcag.bubblegum.util.Util;

@Named
@RequestScoped
public class SettingsBean {

	private String password = "";
	private User user;
	private String status = "";

	@Inject
	private SessionBean sessionBean;

	@Inject
	private UserService userService;

	@Inject
	private IMailService mailService;
	
	@PostConstruct
	public void init() {
		user = userService.getById(sessionBean.getUserID());
		status = user.getStatus();
	}

	
	public boolean isVerified() {
		return user.isEmailVerified();
	}
	
	public String sendVerificationEmail() {
		mailService.sendAuthenticationKey(user);
		return null;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String logout() {
		sessionBean.invalidateSession();
		return "home";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void saveSettings() {
		if (!password.isEmpty())
			user.setPassword(Util.encodePassword(password));
		if (!status.isEmpty())
			user.setStatus(status);
		userService.update(user);

	}

	public void prepare() {
		user = userService.getById(sessionBean.getUserID());
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
