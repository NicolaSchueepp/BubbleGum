package ch.bbcag.bubblegum.bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class LogoutBean implements Serializable {
	private static final long serialVersionUID = -7585723027391394711L;

	@Inject
	private SessionBean sessionBean;

	public String logout() {
		sessionBean.invalidateSession();
		return "home";
	}
}
