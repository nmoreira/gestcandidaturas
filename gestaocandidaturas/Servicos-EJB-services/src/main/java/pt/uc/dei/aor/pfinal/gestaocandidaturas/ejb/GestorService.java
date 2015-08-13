package pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Gestor;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Utilizador;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.GestorFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.IGestorFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.IUtilizadorFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.UtilizadorFacade;

/**
 * Session Bean implementation class AdministradorService
 */
@Stateless
@LocalBean
public class GestorService {

	@EJB(beanInterface = GestorFacade.class)
	private IGestorFacade gestorFacade;

	@EJB(beanInterface = UtilizadorFacade.class)
	private IUtilizadorFacade userFacade;

	/**
	 * Default constructor.
	 */
	public GestorService() {
		// TODO Auto-generated constructor stub
	}

	public Gestor convertUtilizadorInGestor(long userId) {
		Utilizador user = userFacade.find(userId);
		Gestor newGest = new Gestor(user);
		newGest.setPerfil(gestorFacade.getPerfilGestor());
		userFacade.delete(user);
		user = null;
		gestorFacade.createBypassingPassword(newGest);
		return gestorFacade.findByLogin(newGest.getLogin());
	}

	public void createNewGestor(Gestor newGest) {
		newGest.setPerfil(gestorFacade.getPerfilGestor());
		gestorFacade.create(newGest);
	}

	public List<Gestor> listaGestores() {
		return (List<Gestor>) gestorFacade.findAll();
	}

}
