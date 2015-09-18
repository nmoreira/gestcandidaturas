package pt.uc.dei.aor.pfinal.gestaocandidaturas.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.CandidaturaService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidatura;

@Named
@ViewScoped
public class CandidaturasEspontaneas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CandidaturaService candidaturaServ;

	private List<Candidatura> candidaturasEspontaneas;

	@PostConstruct
	private void init() {
		candidaturasEspontaneas = candidaturaServ.getCandidaturasEspontaneas();
	}

	public List<Candidatura> getCandidaturasEspontaneas() {
		return candidaturasEspontaneas;
	}

	public void setCandidaturasEspontaneas(
			List<Candidatura> candidaturasEspontaneas) {
		this.candidaturasEspontaneas = candidaturasEspontaneas;
	}

}
