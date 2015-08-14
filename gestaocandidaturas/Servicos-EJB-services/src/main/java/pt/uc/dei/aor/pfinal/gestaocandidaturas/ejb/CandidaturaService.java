package pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidatura;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.CandidaturaFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.ICandidaturaFacade;

/**
 * Session Bean implementation class AdministradorService
 */
@Stateless
@LocalBean
public class CandidaturaService {

	@EJB(beanInterface = CandidaturaFacade.class)
	private ICandidaturaFacade candidaturaFacade;

	/**
	 * Default constructor.
	 */
	public CandidaturaService() {
		// TODO Auto-generated constructor stub
	}

	public void createNewCandidatura(Candidatura candidatura) {
		candidaturaFacade.create(candidatura);
	}

	public Candidatura getCandidatura(long candidaturaid) {
		return candidaturaFacade.find(candidaturaid);
	}

	public List<Candidatura> getCandidaturasByPosicaoId(long posicaoId) {
		return candidaturaFacade.findByPosicao(posicaoId);
	}

	public List<Candidatura> getCandidaturasByCandidatoId(long candidatoId) {
		return candidaturaFacade.findByCandidato(candidatoId);
	}
}
