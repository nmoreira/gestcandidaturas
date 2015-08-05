package pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class DisplayMessages {

	public static void addInfoMessage(String message) {
		FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, message,
				"Informação");
		FacesContext.getCurrentInstance().addMessage(null, m);
	}

	public static void addErrorMessage(String message) {
		FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, message,
				"Erro");
		FacesContext.getCurrentInstance().addMessage(null, m);
	}

	public static void addWarnMessage(String message) {
		FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, message,
				"Aviso");
		FacesContext.getCurrentInstance().addMessage(null, m);
	}

}
