package pt.uc.dei.aor.pfinal.gestaocandidaturas.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.AdministradorService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.EntrevistadorService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.GestorService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.UtilizadorService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Administrador;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Entrevistador;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Gestor;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Utilizador;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.DisplayMessages;

@Named
@ViewScoped
public class EditarPerfil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CurrentSession current;

	@Inject
	private UtilizadorService userServ;

	@Inject
	private AdministradorService adminService;

	@Inject
	private EntrevistadorService entService;

	@Inject
	private GestorService gestService;

	private Utilizador utilizador;

	private String login;
	private String email;

	@PostConstruct
	private void init() {
		utilizador = current.getCurrentUser();
		this.login = this.utilizador.getLogin();
		this.email = this.utilizador.getEmail();
	}

	public void validaLogin() {
		if (!this.login.equals(utilizador.getLogin())) {
			if (userServ.existeLogin(login) == true)
				DisplayMessages.addErrorMessage("O login " + login
						+ " já está em uso! Por favor escolha outro");
			else {
				DisplayMessages
						.addInfoMessage("Login " + login + " disponível");
			}
		}
	}

	public void validaEmail() {
		if (!this.email.equals(utilizador.getEmail())) {
			if (userServ.existeEmail(email) == true) {
				DisplayMessages.addErrorMessage("O email " + email
						+ " já está registado!");
			}
		}
	}

	public void atualizarUtilizador() {
		boolean valida = true;
		if (!this.login.equals(utilizador.getLogin())) {
			if (userServ.existeLogin(login) == true) {
				DisplayMessages.addErrorMessage("O login " + login
						+ " já está em uso! Por favor escolha outro");
				valida = false;
			} else {
				utilizador.setLogin(login);
			}
		} else if (!this.email.equals(utilizador.getEmail())) {
			if (userServ.existeEmail(email) == true) {
				DisplayMessages.addErrorMessage("O email " + email
						+ " já está registado!");
				valida = false;
			} else {
				utilizador.setEmail(login);
			}
		}
		if (valida) {
			if (utilizador.getCargo().equals("ADMIN")) {
				Administrador admin = (Administrador) utilizador;
				if (adminService.atualizarAdministrador(admin)) {
					DisplayMessages.addInfoMessage("Conta de Administrador de "
							+ admin.getNome() + " atualizada com sucesso!");
				} else {
					DisplayMessages
							.addErrorMessage("Erro ao atualizar a sua conta de Administrador");
				}
			} else if (utilizador.getCargo().equals("GESTOR")) {
				Gestor gestor = (Gestor) utilizador;

				if (gestService.atualizarGestor(gestor)) {
					DisplayMessages.addInfoMessage("Conta de Gestor de "
							+ gestor.getNome() + " atualizada com sucesso!");
				} else {
					DisplayMessages
							.addErrorMessage("Erro ao atualizar a sua conta de Gestor");
				}
			} else if (utilizador.getCargo().equals("ENTREVISTADOR")) {
				Entrevistador ent = (Entrevistador) utilizador;
				if (entService.atualizarEntrevistador(ent)) {
					DisplayMessages.addInfoMessage("Conta de Entrevistador de "
							+ ent.getNome() + " atualizada com sucesso!");
				} else {
					DisplayMessages
							.addErrorMessage("Erro ao atualizar a sua conta de Entrevistador");
				}
			}
		}
	}

	public Utilizador getUtilizador() {
		return utilizador;
	}

	public void setUtilizador(Utilizador utilizador) {
		this.utilizador = utilizador;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
