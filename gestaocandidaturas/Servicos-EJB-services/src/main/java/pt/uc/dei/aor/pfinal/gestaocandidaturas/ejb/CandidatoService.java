package pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidato;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Utilizador;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.CandidatoFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.ICandidatoFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.IUtilizadorFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.UtilizadorFacade;

/**
 * Session Bean implementation class CandidatoService
 */
@Stateless
@LocalBean
public class CandidatoService {

	@EJB(beanInterface = CandidatoFacade.class)
	private ICandidatoFacade candidatoFacade;

	@EJB(beanInterface = UtilizadorFacade.class)
	private IUtilizadorFacade userFacade;

	static Logger logger = LoggerFactory.getLogger(CandidatoService.class);

	/**
	 * Default constructor.
	 */
	public CandidatoService() {
		// TODO Auto-generated constructor stub
	}

	public Candidato convertUtilizadorInCandidato(long userId) {
		Utilizador user = userFacade.find(userId);
		Candidato newCandidato = new Candidato(user);
		newCandidato.setPerfil(candidatoFacade.getPerfilCandidato());
		userFacade.delete(user);
		candidatoFacade.createBypassingPassword(newCandidato);
		newCandidato = candidatoFacade.findByLogin(user.getLogin());
		logger.info("Utilizador com login " + newCandidato.getLogin()
				+ " convertido com sucesso em Candidato");
		return newCandidato;
	}

	public void createNewCandidato(Candidato newCandidato) {
		newCandidato.setPerfil(candidatoFacade.getPerfilCandidato());
		candidatoFacade.create(newCandidato);
	}

	public boolean atualizarCandidato(Candidato cand) {
		if (candidatoFacade.update(cand) != null) {
			return true;
		} else {
			return false;
		}
	}

	public Candidato getCandidatoById(long id) {
		return candidatoFacade.find(id);
	}

	public Candidato getCandidatoByEmail(String email) {
		return candidatoFacade.findByEmail(email);
	}

	public Candidato getCandidatoByLogin(String login) {
		return candidatoFacade.findByLogin(login);
	}

	public List<String> getCartas(long id) {
		return candidatoFacade.getCartasFromCandidatoId(id);
	}

	public boolean adicionarCartaMotivacao(long id, String cartaUrl) {
		return candidatoFacade.addCartaMotivacao(id, cartaUrl);
	}

	public List<Candidato> pesquisaCandidatoByNome(String nome) {
		return candidatoFacade.searchCandidatoByNome(nome);
	}

	public List<Candidato> pesquisaCandidatoByApelido(String apelido) {
		return candidatoFacade.searchCandidatoByApelido(apelido);
	}

	public List<Candidato> pesquisaCandidatoByEmail(String email) {
		return candidatoFacade.searchCandidatoByEmail(email);
	}

	public List<Candidato> pesquisaCandidatoByMorada(String morada) {
		return candidatoFacade.searchCandidatoByMorada(morada);
	}

	public List<Candidato> pesquisaCandidatoByCidade(String cidade) {
		return candidatoFacade.searchCandidatoByCidade(cidade);
	}

	public List<Candidato> pesquisaCandidatoByTelefone(long telefone) {
		return candidatoFacade.searchCandidatoByTelefone(telefone);
	}

	public List<Candidato> pesquisaCandidatoByTelemovel(long telemovel) {
		return candidatoFacade.searchCandidatoByTelemovel(telemovel);
	}

	public List<Candidato> pesquisaCandidatoByPais(String pais) {
		return candidatoFacade.searchCandidatoByPais(pais);
	}

	public List<Candidato> pesquisaCandidatoByCurso(String curso) {
		return candidatoFacade.searchCandidatoByCurso(curso);
	}

	public List<Candidato> pesquisaCandidatoByEscola(String escola) {
		return candidatoFacade.searchCandidatoByEscola(escola);
	}

}
