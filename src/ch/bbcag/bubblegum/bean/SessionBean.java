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
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		session.invalidate();
	}
}
