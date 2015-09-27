package pt.uc.dei.aor.pfinal.gestaocandidaturas.view;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
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
import pt.uc.dei.aor.pfinal.gestaocandidaturas.mail.CommonsMail;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.DisplayMessages;

@Named
@ViewScoped
public class DefinirCargo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private UtilizadorService userService;

	@Inject
	private AdministradorService adminService;

	@Inject
	private EntrevistadorService entService;

	@Inject
	private GestorService gestService;

	@Inject
	private CommonsMail mail;

	private List<Utilizador> users;
	private Utilizador user;
	private String confirmString;

	@PostConstruct
	private void init() {
		consultaListaUtilizadoresSemPerfil();
	}

	public void utilizadorSelecionado() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String userLogin = params.get("userlogin");
		String novoCargo = params.get("cargo");
		user = userService.getUtilizadorByLogin(userLogin);
		setConfirmString("Confirma a conversão do utilizador " + user.getNome()
				+ " " + user.getApelido() + " em " + novoCargo + "?");
	}

	private void consultaListaUtilizadoresSemPerfil() {
		setUsers(userService.getUtilizadoresSemPerfil());
	}

	public void converterUserEmAdmin() {
		Administrador admin = adminService
				.convertUtilizadorInAdministrador(user.getId());
		if (admin != null) {
			DisplayMessages.addInfoMessage(user.getNome() + " "
					+ user.getApelido()
					+ " convertido com sucesso em Administrador");
			mail.enviaEmailSimples(
					"Perfil atribuido",
					"Um administrador do sistema, já lhe atribuiu o perfil de Administrador.\n"
							+ "A partir de agora pode iniciar sessão no sistema e começar a sua atividade",
					admin);
		} else {
			DisplayMessages.addErrorMessage("Erro ao converter "
					+ user.getNome() + " " + user.getApelido()
					+ " em Administrador");
		}
		consultaListaUtilizadoresSemPerfil();
	}

	public void converterUserEmEntrevistador() {
		Entrevistador ent = entService.convertUtilizadorInEntrevistador(user
				.getId());
		if (ent != null) {
			DisplayMessages.addInfoMessage(user.getNome() + " "
					+ user.getApelido()
					+ " convertido com sucesso em Entrevistador");
			mail.enviaEmailSimples(
					"Perfil atribuido",
					"Um administrador do sistema, já lhe atribuiu o perfil de Entrevistador.\n"
							+ "A partir de agora pode iniciar sessão no sistema e começar a sua atividade",
					ent);
		} else {
			DisplayMessages.addErrorMessage("Erro ao converter "
					+ user.getNome() + " " + user.getApelido()
					+ " em Entrevistador");
		}
		consultaListaUtilizadoresSemPerfil();
	}

	public void converterUserEmGestor() {
		Gestor gest = gestService.convertUtilizadorInGestor(user.getId());
		if (gest != null) {
			DisplayMessages.addInfoMessage(user.getNome() + " "
					+ user.getApelido() + " convertido com sucesso em Gestor");
			mail.enviaEmailSimples(
					"Perfil atribuido",
					"Um administrador do sistema, já lhe atribuiu o perfil de Gestor.\n"
							+ "A partir de agora pode iniciar sessão no sistema e começar a sua atividade",
					gest);
		} else {
			DisplayMessages.addErrorMessage("Erro ao converter "
					+ user.getNome() + " " + user.getApelido() + " em Gestor");
		}
		consultaListaUtilizadoresSemPerfil();
	}

	public List<Utilizador> getUsers() {
		return users;
	}

	public void setUsers(List<Utilizador> users) {
		this.users = users;
	}

	public Utilizador getUser() {
		return user;
	}

	public void setUser(Utilizador user) {
		this.user = user;
	}

	public String getConfirmString() {
		return confirmString;
	}

	public void setConfirmString(String confirmString) {
		this.confirmString = confirmString;
	}
}
