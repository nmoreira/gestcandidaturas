package pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidatura;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Posicao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.CandidaturaFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.ICandidaturaFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.IPosicaoFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.PosicaoFacade;

/**
 * Session Bean implementation class AdministradorService
 */
@Stateless
@LocalBean
public class CandidaturaService {

	@EJB(beanInterface = CandidaturaFacade.class)
	private ICandidaturaFacade candidaturaFacade;

	@EJB(beanInterface = PosicaoFacade.class)
	private IPosicaoFacade posicaoFacade;

	/**
	 * Default constructor.
	 */
	public CandidaturaService() {
		// TODO Auto-generated constructor stub
	}

	public boolean createNewCandidatura(Candidatura candidatura) {
		if (candidaturaFacade.create(candidatura) != null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean criaCandidaturaEspontanea(Candidatura candidatura) {
		return candidaturaFacade.criaCandidaturaEspontanea(candidatura);
	}

	public List<Candidatura> listaCandidaturas() {
		return (List<Candidatura>) candidaturaFacade.findAll();
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

	public Candidatura getCandidaturaByCandidatoIdAndPosicaoId(
			long candidatoId, long posicaoId) {
		return candidaturaFacade.findByCandidatoAndPosicao(candidatoId,
				posicaoId);
	}

	public List<Candidatura> pesquisaCandidaturasByCandidatoNome(String nome) {
		return candidaturaFacade.searchCandidaturaByCandidatoNome(nome);
	}

	public List<Candidatura> pesquisaCandidaturasByCandidatoApelido(
			String apelido) {
		return candidaturaFacade.searchCandidaturaByCandidatoApelido(apelido);
	}

	public List<Candidatura> pesquisaCandidaturasByCandidatoEmail(String email) {
		return candidaturaFacade.searchCandidaturaByCandidatoEmail(email);
	}

	public List<Candidatura> pesquisaCandidaturasByCandidatoMorada(String morada) {
		return candidaturaFacade.searchCandidaturaByCandidatoMorada(morada);
	}

	public List<Candidatura> pesquisaCandidaturasByCandidatoCidade(String cidade) {
		return candidaturaFacade.searchCandidaturaByCandidatoCidade(cidade);
	}

	public List<Candidatura> pesquisaCandidaturasByCandidatoTelefone(
			long telefone) {
		return candidaturaFacade.searchCandidaturaByCandidatoTelefone(telefone);
	}

	public List<Candidatura> pesquisaCandidaturasByCandidatoTelemovel(
			long telemovel) {
		return candidaturaFacade
				.searchCandidaturaByCandidatoTelemovel(telemovel);
	}

	public List<Candidatura> pesquisaCandidaturasByCandidatoPais(String pais) {
		return candidaturaFacade.searchCandidaturaByCandidatoPais(pais);
	}

	public List<Candidatura> pesquisaCandidaturasByCandidatoCurso(String curso) {
		return candidaturaFacade.searchCandidaturaByCandidatoCurso(curso);
	}

	public List<Candidatura> pesquisaCandidaturasByCandidatoEscola(String escola) {
		return candidaturaFacade.searchCandidaturaByCandidatoEscola(escola);
	}

	public List<Candidatura> getCandidaturasEspontaneas() {
		return candidaturaFacade.getCandidaturasEspontaneas();
	}

	public boolean atribuirPosicao(long idCandidatura, long idPosicao) {
		Candidatura cand = candidaturaFacade.find(idCandidatura);
		Posicao pos = posicaoFacade.find(idPosicao);
		cand.setPosicao(pos);
		if (candidaturaFacade.update(cand) != null) {
			return true;
		} else {
			return false;
		}
	}

}
