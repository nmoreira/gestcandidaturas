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

	public Utilizador getCurrentUser() {
		return currentUser;
	}

	public static void setCurrentUser(Utilizador loggedUser) {
		currentUser = loggedUser;
	}

}
