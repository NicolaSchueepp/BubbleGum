package ch.bbcag.bubblegum.bean;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class ErrorHandler {
	
	public String getStatusCode() {
		String val = String.valueOf((Integer) FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.get("javax.servlet.error.status_code"));
		return val;
	}

	public String getMessage() {
		String val = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.get("javax.servlet.error.message");
		return val;
	}

	public String getExceptionType() {
		String val = FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.get("javax.servlet.error.exception_type").toString();
		return val;
	}

	public String getException() {
		String val = (String) ((Exception) FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.get("javax.servlet.error.exception")).toString();
		return val;
	}

	public String getRequestURI() {
		return (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.get("javax.servlet.error.request_uri");
	}

	public String getServletName() {
		return (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.get("javax.servlet.error.servlet_name");
	}
}
