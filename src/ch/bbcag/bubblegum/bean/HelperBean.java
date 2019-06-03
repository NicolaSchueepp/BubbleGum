package ch.bbcag.bubblegum.bean;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import ch.bbcag.bubblegum.util.message.MessageArray;

@Named
@RequestScoped
public class HelperBean implements Serializable {

	private static final long serialVersionUID = 1L;

//	@Inject
//	private CustomerDao customerDao;
//	
	@Inject
	private MessageArray messageArray;

	private HttpSession session;

//	private boolean isLoggedIn;

//	public String getEmail() {
//		return getId() != 0l ? customerDao.getById(getId()).getEmail() : "";
//	}
//
//	public double getBalance() {
//		return getId() != 0l ? customerDao.getById(getId()).getBalance() : 0l;
//	}

	public long getId() {
		return isLogedin() ? (Long) getSession().get("id") : 0l;
	}

	public boolean isLogedin() {
		return getSession().get("id") != null;
	}

	public String getMessages() {

		return messageArray.toString();
	}

	public Map<String, Object> getSession() {
		Map<String, Object> sessionMap;
		try {
			sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		} catch (NullPointerException e) {
			sessionMap = getSessionMap();
		}
		return sessionMap;
	}

	private Map<String, Object> getSessionMap() {
		Map<String, Object> sessionMap = new HashMap<String, Object>();
		Enumeration<String> names = session.getAttributeNames();
		String name;
		Object value;
		while (names.hasMoreElements()) {
			name = names.nextElement();
			value = session.getAttribute(name);
			sessionMap.put(name, value);
		}
		return sessionMap;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

}
