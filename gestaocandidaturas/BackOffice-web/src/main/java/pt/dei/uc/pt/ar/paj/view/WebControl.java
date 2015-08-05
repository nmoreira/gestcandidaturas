package pt.dei.uc.pt.ar.paj.view;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Administrador;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.AdministradorFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.IAdministradorFacade;
import dei.uc.pt.ar.paj.service.core.UserServices;

@Named
@SessionScoped
public class WebControl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6281455009512570513L;

	@Inject
	private UserServices serv;

	@EJB(beanInterface = AdministradorFacade.class)
	private IAdministradorFacade af;

	public String getHello() {

		Administrador adm = new Administrador();

		adm.setNome("nome do admin");
		adm.setEmail("email do admin" + Math.random());
		adm.setPerfil(af.getPerfilAdmin());

		af.create(adm);

		return serv.sayHello();
	}

	public void setHello() {
		// nothing
	}

}
