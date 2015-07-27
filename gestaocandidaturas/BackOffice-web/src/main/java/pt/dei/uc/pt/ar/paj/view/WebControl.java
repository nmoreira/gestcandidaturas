package pt.dei.uc.pt.ar.paj.view;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

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

	public String getHello() {
		return serv.sayHello();
	}

	public void setHello() {
		// nothing
	}

}
