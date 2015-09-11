package pt.uc.dei.aor.pfinal.gestaocandidaturas.view;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.CandidatoService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.CandidaturaService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidato;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidatura;

@Named
@ViewScoped
public class VerCandidato implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Candidato candidato;
	private List<Candidatura> candidaturas;

	@Inject
	private CandidatoService candidatoServ;

	@Inject
	private CandidaturaService candidaturaServ;

	@PostConstruct
	private void init() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		long candidatoid = Long.parseLong(params.get("candidatoid"));

		this.setCandidato(candidatoServ.getCandidatoById(candidatoid));

		this.candidaturas = candidaturaServ
				.getCandidaturasByCandidatoId(candidatoid);
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public List<Candidatura> getCandidaturas() {
		return candidaturas;
	}

	public void setCandidaturas(List<Candidatura> candidaturas) {
		this.candidaturas = candidaturas;
	}

}
