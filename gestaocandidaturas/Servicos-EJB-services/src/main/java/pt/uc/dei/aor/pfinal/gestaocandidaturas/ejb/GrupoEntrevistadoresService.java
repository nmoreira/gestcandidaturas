package pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.GrupoEntrevistadores;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.GrupoEntrevistadoresFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.IGrupoEntrevistadoresFacade;

/**
 * Session Bean implementation class AdministradorService
 */
@Stateless
@LocalBean
public class GrupoEntrevistadoresService {

	@EJB(beanInterface = GrupoEntrevistadoresFacade.class)
	private IGrupoEntrevistadoresFacade grupoEntrevistadoresFacade;

	/**
	 * Default constructor.
	 */
	public GrupoEntrevistadoresService() {
		// TODO Auto-generated constructor stub
	}

	public List<GrupoEntrevistadores> getAllEntrevistadores() {
		return grupoEntrevistadoresFacade.findAll();
	}

	public GrupoEntrevistadores getEntrevistadoresById(long id) {
		return grupoEntrevistadoresFacade.findById(id);
	}
}
