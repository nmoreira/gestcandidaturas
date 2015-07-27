package dei.uc.pt.ar.paj.service.core;

import java.io.Serializable;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dei.uc.pt.ar.paj.adomain.entities.SimpleUser;
import dei.uc.pt.ar.paj.adomain.facade.ISimpleUserFacade;

/**
 * @author jncor
 *
 */
@Stateless
public class UserServices implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1284938387113138356L;

	@Inject
	private ISimpleUserFacade usermng;

	public String sayHello() {
		SimpleUser a_usr = new SimpleUser();
		a_usr.setUsername("teste");

		// Here: a_usr has a ref
		// SimpleUser a_usr_with_id = usermng.create(a_usr);
		// Here: ref as changed (CAREFULL) !! (the returned a_usr has the ID)

		ArrayList<SimpleUser> usr_list = new ArrayList<SimpleUser>();
		usr_list.addAll(usermng.findAll());

		StringBuilder sb = new StringBuilder();
		for (SimpleUser usr : usr_list)
			sb.append(usr.toString()).append(" ");

		return "hello to: " + sb.toString();
	}

}