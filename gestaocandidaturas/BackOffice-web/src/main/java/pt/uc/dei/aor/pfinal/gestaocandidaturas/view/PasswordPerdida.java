package pt.uc.dei.aor.pfinal.gestaocandidaturas.view;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.UtilizadorService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Utilizador;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.mail.CommonsMail;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.DisplayMessages;

@Named
@ViewScoped
public class PasswordPerdida implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String email;

	@Inject
	private UtilizadorService utilizadorServ;

	@Inject
	private CommonsMail mail;

	public void novaPassword() {
		if (utilizadorServ.existeEmail(email)) {
			String pass = utilizadorServ.geraNovaPassword(email);
			if (pass != null) {
				Utilizador u = utilizadorServ.getUtilizadorByEmail(email);
				mail.enviaEmailSimples(
						"Gestão de candidaturas - Nova password",
						"Foi solicitada uma alteração de password, na conta associada ao seu endereço de email.\n\n"
								+ "Login: "
								+ u.getLogin()
								+ "\n"
								+ "NOVA PASSWORD: "
								+ pass
								+ "\n\n"
								+ "Por favor inicie sessão no site, e altere a password o mais brevemente possível!",
						u);
				DisplayMessages
						.addInfoMessage("Foi enviada uma nova password para o endereço de email: "
								+ u.getEmail());
			} else {
				DisplayMessages
						.addErrorMessage("Erro ao gerar uma nova password");
			}
		} else {
			DisplayMessages
					.addErrorMessage("O email introduzido não se encontra registado!");
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
