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

}
