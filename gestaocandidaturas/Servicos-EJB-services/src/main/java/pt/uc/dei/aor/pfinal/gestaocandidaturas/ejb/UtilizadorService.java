package pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Utilizador;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.IUtilizadorFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.UtilizadorFacade;

/**
 * Session Bean implementation class UtilizadorService
 */
@Stateless
@LocalBean
public class UtilizadorService {

	@EJB(beanInterface = UtilizadorFacade.class)
	private IUtilizadorFacade userFacade;

	/**
	 * Default constructor.
	 */
	public UtilizadorService() {
		// TODO Auto-generated constructor stub
	}

	public void createNewUtilizador(Utilizador newUser) {
		userFacade.create(newUser);
	}

	public boolean existeLogin(String login) {
		if (userFacade.findByLogin(login) == null)
			return false;
		else
			return true;
	}

	public boolean existeEmail(String email) {
		if (userFacade.findByEmail(email) == null)
			return false;
		else
			return true;
	}

}
