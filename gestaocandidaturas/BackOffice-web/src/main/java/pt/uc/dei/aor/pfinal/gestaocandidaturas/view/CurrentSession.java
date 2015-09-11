package pt.uc.dei.aor.pfinal.gestaocandidaturas.view;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Utilizador;

@Named
@SessionScoped
public class CurrentSession implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Utilizador currentUser;

	private static String userFirstName = "Visitante";
	private static boolean logged = false;

	public Utilizador getCurrentUser() {
		return currentUser;
	}

	public static void setCurrentUser(Utilizador loggedUser) {
		if (loggedUser == null) {
			userFirstName = "Visitante";
			logged = false;
		} else {
			userFirstName = loggedUser.getNome();
			logged = true;
		}
		currentUser = loggedUser;
	}

	// public String getUserHome() {
	// if (currentUser == null) {
	// return "/paginas/login.xhtml";
	// } else {
	// String outcome;
	// String cargo = currentUser.getCargo();
	// if (cargo.equals("ADMIN")) {
	// outcome = "/paginas/homeadministrador.xhtml";
	// } else if (cargo.equals("GESTOR")) {
	// outcome = "/paginas/homegestor.xhtml";
	// } else if (cargo.equals("ENTREVISTADOR")) {
	// outcome = "/paginas/homeentrevistador.xhtml";
	// } else {
	// outcome = "/paginas/home.xhtml?faces-redirect=true";
	// }
	// return outcome;
	// }
	// }

	public String getUserFirstName() {
		return userFirstName;
	}

	public boolean isLogged() {
		return logged;
	}
}
