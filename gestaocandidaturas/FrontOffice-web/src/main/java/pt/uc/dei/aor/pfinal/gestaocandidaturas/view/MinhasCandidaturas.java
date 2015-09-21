package pt.uc.dei.aor.pfinal.gestaocandidaturas.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.CandidatoService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.CandidaturaService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.PosicaoService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidato;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidatura;

@Named
@ViewScoped
public class MinhasCandidaturas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CurrentSession current;

	@Inject
	private CandidaturaService candidaturaServ;

	@Inject
	private CandidatoService candidatoServ;

	@Inject
	private PosicaoService posicaoServ;

	private Candidato candidato;

	@PostConstruct
	private void init() {
		this.candidato = candidatoServ.getCandidatoByLogin(current
				.getCurrentUser().getLogin());
		List<Candidatura> candidaturas = candidaturaServ
				.getCandidaturasByCandidatoId(candidato.getId());
		for (Candidatura candidatura : candidaturas) {
			long id = candidatura.getPosicao().getId();
			candidatura.setPosicao(posicaoServ.getPosicaoById(id));
		}
		this.candidato.setCandidaturas(candidaturas);
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

}
