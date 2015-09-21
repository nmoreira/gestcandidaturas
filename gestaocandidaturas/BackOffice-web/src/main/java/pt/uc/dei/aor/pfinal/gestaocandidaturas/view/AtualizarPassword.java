package pt.uc.dei.aor.pfinal.gestaocandidaturas.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.UtilizadorService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Utilizador;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.DisplayMessages;

@Named
@ViewScoped
public class AtualizarPassword implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private UtilizadorService userServ;

	@Inject
	private CurrentSession current;

	private String antigaPass;
	private String novaPass;

	private Utilizador user;

	@PostConstruct
	public void init() {
		this.user = current.getCurrentUser();
	}

	private boolean validaPassAntiga() {
		if (userServ.passwordMatch(user.getId(), antigaPass))
			return true;
		else
			return false;
	}

	public void alteraPass() {
		if (validaPassAntiga()) {
			if (userServ.alteraPassword(user.getId(), novaPass)) {
				DisplayMessages.addInfoMessage("Password alterada com sucesso");
			} else {
				DisplayMessages
						.addErrorMessage("Falha ao atualizar a password");
			}
		} else {
			DisplayMessages.addErrorMessage("A password atual não é válida!");
		}
	}

	public String getNovaPass() {
		return novaPass;
	}

	public void setNovaPass(String novaPass) {
		this.novaPass = novaPass;
	}

	public String getAntigaPass() {
		return antigaPass;
	}

	public void setAntigaPass(String antigaPass) {
		this.antigaPass = antigaPass;
	}

}
