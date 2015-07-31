package pt.dei.uc.pt.ar.paj.view;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.TesteEJB;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Administrador;
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

	@Inject
	private TesteEJB ejb;

	public String getHello() {

		Administrador adm = new Administrador();
		adm.setNome("nome do admin");
		adm.setEmail("email do admin");

		ejb.saveUser(adm);

		return serv.sayHello();
	}

	public void setHello() {
		// nothing
	}

}
