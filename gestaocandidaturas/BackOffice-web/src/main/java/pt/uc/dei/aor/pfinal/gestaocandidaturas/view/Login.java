package pt.uc.dei.aor.pfinal.gestaocandidaturas.view;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.UtilizadorService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Utilizador;
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
			String outcome;

			request.login(login, password);
			Utilizador u = userServ.getUtilizadorByLogin(login);
			CurrentSession.setCurrentUser(u);

			System.out.println("login ok: " + login);
			String cargo = u.getCargo();

			if (cargo.equals("ADMIN") || cargo.equals("GESTOR")
					|| cargo.equals("ENTREVISTADOR")) {
				outcome = "/paginas/home.xhtml?faces-redirect=true";
			} else {

				// if (cargo.equals("ADMIN")) {
				// outcome = "/paginas/home.xhtml?faces-redirect=true"; //
				// "/paginas/homeadministrador.xhtml?faces-redirect=true";
				// } else if (cargo.equals("GESTOR")) {
				// outcome = "/paginas/home.xhtml?faces-redirect=true"; //
				// "/paginas/homegestor.xhtml?faces-redirect=true";
				// } else if (cargo.equals("ENTREVISTADOR")) {
				// outcome = "/paginas/home.xhtml?faces-redirect=true"; //
				// "/paginas/homeentrevistador.xhtml?faces-redirect=true";
				// } else {
				request.logout();
				CurrentSession.setCurrentUser(null);
				DisplayMessages.addWarnMessage("Perfil inválido!");
				outcome = "";
				return outcome;
			}
			DisplayMessages
					.addInfoMessage("Sessão iniciada com sucesso! Bem-vindo "
							+ u.getNome() + " " + u.getApelido() + "!");
			return outcome;
		} catch (ServletException e) {
			DisplayMessages
					.addErrorMessage("Falha no login! Por favor tente novamente!");
			try {
				request.logout();
			} catch (ServletException e1) {
				DisplayMessages.addErrorMessage("Falha no autologout");
			}
			return "";
		}
	}

	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		try {
			request.logout();
			CurrentSession.setCurrentUser(null);
			return "/paginas/home.xhtml?faces-redirect=true";
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
