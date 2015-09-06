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
		System.out.println("posicao definida: " + this.posicao);
	}

}
