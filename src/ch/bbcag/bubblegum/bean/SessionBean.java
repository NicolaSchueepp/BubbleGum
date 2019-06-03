package ch.bbcag.bubblegum.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@SessionScoped
public class SessionBean implements Serializable {
	private static final long serialVersionUID = -232299624744349288L;

	private Long userID = null;

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public void invalidateSession() {
		HttpSession session = getSession();
		session.invalidate();
	}

	public void addLogedIn() {
		HttpSession session = getSession();
		session.setAttribute("isLoggedIn", true);
	}

	public HttpSession getSession() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		return (HttpSession) facesContext.getExternalContext().getSession(true);
	}
}
