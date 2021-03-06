package pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

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

	public boolean deleteUtilizador(Utilizador user) {
		return userFacade.delete(user);
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

	public boolean passwordMatch(long userId, String password) {
		return userFacade.passwordMatch(userId, password);
	}

	public boolean alteraPassword(long userId, String novaPassword) {
		return userFacade.changePassword(userId, novaPassword);
	}

	public List<Utilizador> getUtilizadoresSemPerfil() {
		return userFacade.getUtilizadoresSemPerfil();
	}

	public Utilizador getUtilizadorByLogin(String login) {
		return userFacade.findByLogin(login);
	}

	public Utilizador getUtilizadorByEmail(String email) {
		return userFacade.findByEmail(email);
	}

	public List<Utilizador> listaUtilizadores() {
		return (List<Utilizador>) userFacade.findAll();
	}

	public String geraNovaPassword(String email) {
		Utilizador user = userFacade.findByEmail(email);
		String newPass = getRandomPass();
		if (userFacade.changePassword(user.getId(), newPass))
			return newPass;
		else
			return null;
	}

	private String getRandomPass() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}

}
