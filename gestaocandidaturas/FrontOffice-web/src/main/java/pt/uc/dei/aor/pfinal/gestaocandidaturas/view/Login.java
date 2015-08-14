package pt.uc.dei.aor.pfinal.gestaocandidaturas.view;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.UtilizadorService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.DisplayMessages;

@Named
@RequestScoped
public class Login {

	private String login;
	private String password;

	@Inject
	private UtilizadorService userServ;

	public String login() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {
			request.login(login, password);
			CurrentSession.setCurrentUser(userServ.getUtilizadorByLogin(login));
			System.out.println("login ok: " + login);
			return "/areaprotegida/index2?faces-redirect=true";
		} catch (ServletException e) {
			DisplayMessages.addErrorMessage("Falha no login");
			return "/errologin?faces-redirect=true";
		}
	}

	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		try {
			request.logout();
			CurrentSession.setCurrentUser(null);
			return "/testes/loginfront?faces-redirect=true";
		} catch (ServletException e) {
			DisplayMessages.addErrorMessage("Falha no Logout");
			return "";
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
