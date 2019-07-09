package ch.bbcag.bubblegum.bean;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ch.bbcag.bubblegum.service.IMailService;

@Named
@RequestScoped
public class VerifyEmailBean {

	@Inject
	private IMailService mailService;
	
	public void verifyEmail() {
		mailService.verifyEmail(getKey());
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public String getKey(){
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("key");
	}
}
