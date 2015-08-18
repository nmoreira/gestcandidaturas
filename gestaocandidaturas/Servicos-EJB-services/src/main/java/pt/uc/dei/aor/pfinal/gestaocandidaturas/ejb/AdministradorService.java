package pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Administrador;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Utilizador;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.AdministradorFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.IAdministradorFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.IUtilizadorFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.UtilizadorFacade;

/**
 * Session Bean implementation class AdministradorService
 */
@Stateless
@LocalBean
public class AdministradorService {

	@EJB(beanInterface = AdministradorFacade.class)
	private IAdministradorFacade adminFacade;

	@EJB(beanInterface = UtilizadorFacade.class)
	private IUtilizadorFacade userFacade;

	static Logger logger = LoggerFactory.getLogger(AdministradorService.class);

	/**
	 * Default constructor.
	 */
	public AdministradorService() {
		// TODO Auto-generated constructor stub
	}

	public Administrador convertUtilizadorInAdministrador(long userId) {
		Utilizador user = userFacade.find(userId);
		Administrador newAdmin = new Administrador(user);
		newAdmin.setPerfil(adminFacade.getPerfilAdmin());
		userFacade.delete(user);
		user = null;
		adminFacade.createBypassingPassword(newAdmin);
		newAdmin = adminFacade.findByLogin(newAdmin.getLogin());
		logger.info("Utilizador com login " + newAdmin.getLogin()
				+ " convertido com sucesso em Administrador");
		return newAdmin;
	}

	public void createNewAdministrador(Administrador newAdmin) {
		newAdmin.setPerfil(adminFacade.getPerfilAdmin());
		adminFacade.create(newAdmin);
	}

	public List<Administrador> listaAdministradores() {
		return (List<Administrador>) adminFacade.findAll();
	}

}
