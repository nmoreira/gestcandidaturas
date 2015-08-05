package pt.dei.uc.pt.ar.paj.view;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class loginutilizador implements Serializable {

	private static final long serialVersionUID = 1L;

	private String login;
	private String nome;
	private String email;

	public void verificarDisponibilidadeLogin() {
		FacesMessage msg = null;

		// simula demora no processamento
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) { }
		
		if ("Joana".equalsIgnoreCase(this.login)) {
			msg = new FacesMessage("Login já está a ser usado.");
			msg.setSeverity(FacesMessage.SEVERITY_WARN);
		} else {
			msg = new FacesMessage("Login disponivel.");
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void verificarDisponibilidadeEmail() {
		FacesMessage msg = null;

		// simula demora no processamento
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) { }
		
		if ("a@email.pt".equalsIgnoreCase(this.email)) {
			msg = new FacesMessage("O email já está a ser usado.");
			msg.setSeverity(FacesMessage.SEVERITY_WARN);
		} else {
			msg = new FacesMessage("Email disponivel.");
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	
	public void logar() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login efetuado!"));
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}