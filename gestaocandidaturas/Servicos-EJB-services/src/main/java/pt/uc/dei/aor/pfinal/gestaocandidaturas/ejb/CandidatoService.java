package pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

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
		return candidatoFacade.findByLogin(user.getLogin());
	}

	public void createNewCandidato(Candidato newCandidato) {
		newCandidato.setPerfil(candidatoFacade.getPerfilCandidato());
		candidatoFacade.create(newCandidato);
	}

}
