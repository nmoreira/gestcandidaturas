package pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Entrevistador;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Utilizador;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.EntrevistadorFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.IEntrevistadorFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.IUtilizadorFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.UtilizadorFacade;

/**
 * Session Bean implementation class AdministradorService
 */
@Stateless
@LocalBean
public class EntrevistadorService {

	@EJB(beanInterface = EntrevistadorFacade.class)
	private IEntrevistadorFacade entFacade;

	@EJB(beanInterface = UtilizadorFacade.class)
	private IUtilizadorFacade userFacade;

	static Logger logger = LoggerFactory.getLogger(EntrevistadorService.class);

	/**
	 * Default constructor.
	 */
	public EntrevistadorService() {
		// TODO Auto-generated constructor stub
	}

	public Entrevistador convertUtilizadorInEntrevistador(long userId) {
		Utilizador user = userFacade.find(userId);
		Entrevistador newEnt = new Entrevistador(user);
		newEnt.setPerfil(entFacade.getPerfilEntrevistador());
		userFacade.delete(user);
		entFacade.createBypassingPassword(newEnt);
		newEnt = entFacade.findByLogin(user.getLogin());
		logger.info("Utilizador com login " + newEnt.getLogin()
				+ " convertido com sucesso em Entrevistador");
		return newEnt;
	}

	public void createNewEntrevistador(Entrevistador newEnt) {
		newEnt.setPerfil(entFacade.getPerfilEntrevistador());
		entFacade.create(newEnt);
	}

	public Entrevistador getEntrevistadorById(long entrevistadorId) {
		return entFacade.find(entrevistadorId);
	}

	public Entrevistador getEntrevistadorByEmail(String email) {
		return entFacade.findByEmail(email);
	}

	public Entrevistador getEntrevistadorByLogin(String login) {
		return entFacade.findByLogin(login);
	}

}
