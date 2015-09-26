package pt.uc.dei.aor.pfinal.gestaocandidaturas.view;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.CandidaturaService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidatura;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Posicao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.mail.CommonsMail;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.DisplayMessages;

@Named
@ViewScoped
public class AtribuirPosicao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CandidaturaService candidaturaServ;

	@Inject
	private CommonsMail mail;

	private Candidatura candidatura;
	private Posicao posicao;

	@PostConstruct
	public void init() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		long candid = Long.parseLong(params.get("candid"));
		this.candidatura = candidaturaServ.getCandidatura(candid);
	}

	public void atribuirPosicao() {
		if (candidaturaServ.atribuirPosicao(candidatura.getId(),
				posicao.getId())) {
			DisplayMessages.addInfoMessage("Posição atribuida com sucesso!");
			mail.enviaEmailSimples(
					"Nova candidatura atribuida a uma posição de que é gestor",
					"Uma candidatura espontânea foi atribuida a uma Posição da qual é o gestor.\n"
							+ "Posição: " + posicao.getTitulo() + "\n"
							+ "Candidato: "
							+ candidatura.getCandidato().getNome() + " "
							+ candidatura.getCandidato().getApelido(), posicao
							.getResponsavel().getEmail());
			mail.enviaEmailSimples(
					"A sua candidatura espontânea foi atribuida a uma posição",
					"A sua candidatura espontânea no site de candidaturas, foi atribuida à posição: "
							+ posicao.getTitulo(), candidatura.getCandidato()
							.getEmail());
		} else {
			DisplayMessages.addWarnMessage("Falha ao atribuir a posição");
		}
	}

	public Candidatura getCandidatura() {
		return candidatura;
	}

	public void setCandidatura(Candidatura candidatura) {
		this.candidatura = candidatura;
	}

	public Posicao getPosicao() {
		return posicao;
	}

	public void setPosicao(Posicao posicao) {
		this.posicao = posicao;
	}

}
