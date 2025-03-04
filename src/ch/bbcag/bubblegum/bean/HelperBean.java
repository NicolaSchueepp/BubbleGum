package ch.bbcag.bubblegum.bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.bbcag.bubblegum.service.UserService;
import ch.bbcag.bubblegum.util.message.MessageArray;

@Named
@RequestScoped
public class HelperBean implements Serializable {

	private static final long serialVersionUID = 292364279347623L;

	@Inject
	private MessageArray messageArray;

	@Inject
	private SessionBean sessionBean;

	@Inject
	private UserService userService;
	
	public String getMessages() {
		String messages = messageArray.toString();
		messageArray.clear();
		return messages;
	}

	public boolean isLogedIn() {
		return sessionBean.getUserID() != null;
	}

	public String getUserName() {
		return isLogedIn() ? userService.getById(sessionBean.getUserID()).getName() : "";
	}
	
	public String getUserName(long id) {
		return isLogedIn() ? userService.getById(id).getName() : "";
	}
}
