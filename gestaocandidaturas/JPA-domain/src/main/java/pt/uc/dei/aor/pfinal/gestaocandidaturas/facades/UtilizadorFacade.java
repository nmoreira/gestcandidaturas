package pt.uc.dei.aor.pfinal.gestaocandidaturas.facades;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Utilizador;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.Encript;

/**
 * Session Bean implementation class UtilizadorFacade
 */
@Stateless
@LocalBean
public class UtilizadorFacade implements IUtilizadorFacade {

	@PersistenceContext(unitName = "gestcandidaturas")
	private EntityManager em;

	static Logger logger = LoggerFactory.getLogger(UtilizadorFacade.class);

	private Query q;

	/**
	 * Default constructor.
	 */
	public UtilizadorFacade() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Utilizador create(Utilizador entity) {
		entity.setPassword(Encript.encript(entity.getPassword()));
		String login = entity.getLogin();
		try {
			em.persist(entity);
			Utilizador newUser = findByLogin(login);
			logger.info("Utilizador " + login
					+ " criado com sucesso na base de dados");
			return newUser;
		} catch (Exception e) {
			logger.error("Erro ao criar o utilizador " + login
					+ " na base de dados");
			return null;
		}
	}

	@Override
	public Utilizador update(Utilizador entity) {
		try {
			Utilizador updatedUser = em.merge(entity);
			logger.info("Utilizador " + entity.getLogin()
					+ " atualizado com sucesso");
			return updatedUser;
		} catch (Exception e) {
			logger.error("Erro ao atualizar o utilizador " + entity.getLogin()
					+ " na base de dados");
			return null;
		}
	}

	@Override
	public boolean delete(Utilizador entity) {
		if (em.find(Utilizador.class, entity.getId()) == null) {
			logger.error("Erro ao apagar o utilizador " + entity.getLogin()
					+ ". Utilizador não encontrado");
			return false;
		} else {
			try {
				Utilizador entityToBeRemoved = em.merge(entity);
				em.remove(entityToBeRemoved);
				em.flush();
				logger.info("Utilizador " + entity.getLogin()
						+ " apagado com sucesso da base de dados");
				return true;
			} catch (Exception e) {
				logger.error("Erro ao apagar o utilizador " + entity.getLogin());
				return false;
			}
		}
	}

	@Override
	public Utilizador find(long pk) {
		TypedQuery<Utilizador> q = em.createNamedQuery("Utilizador.findById",
				Utilizador.class).setParameter("id", pk);
		if (!q.getResultList().isEmpty())
			return q.getResultList().get(0);
		else
			return null;
	}

	@Override
	public Collection<Utilizador> findAll() {
		TypedQuery<Utilizador> q = em.createNamedQuery("Utilizador.findAll",
				Utilizador.class);
		return q.getResultList();
	}

	@Override
	public Utilizador findByLogin(String login) {
		TypedQuery<Utilizador> q = em.createNamedQuery(
				"Utilizador.findByLogin", Utilizador.class).setParameter(
				"login", login);
		if (!q.getResultList().isEmpty())
			return q.getResultList().get(0);
		else
			return null;
	}

	@Override
	public Utilizador findByEmail(String email) {
		TypedQuery<Utilizador> q = em.createNamedQuery(
				"Utilizador.findByEmail", Utilizador.class).setParameter(
				"email", email);
		if (!q.getResultList().isEmpty())
			return q.getResultList().get(0);
		else
			return null;
	}

	@Override
	public List<Utilizador> findByCargo(String cargo) {
		TypedQuery<Utilizador> q = em.createNamedQuery(
				"Utilizador.findByCargo", Utilizador.class);
		return q.getResultList();
	}

	@Override
	public boolean changePassword(long userId, String newPassword) {
		Utilizador user = find(userId);
		user.setPassword(Encript.encript(newPassword));
		if (update(user) != null) {
			logger.info("Password do utilizador " + user.getLogin()
					+ " atualizada com sucesso");
			return true;
		} else {
			logger.error("Falha ao atualizar a password do utilizador "
					+ user.getLogin());
			return false;
		}
	}

	@Override
	public List<Utilizador> getUtilizadoresSemPerfil() {
		List<Utilizador> listaFinal = new ArrayList<>();
		q = em.createQuery("from Utilizador u where u.perfil = null");
		listaFinal = q.getResultList();
		return listaFinal;
	}

	@Override
	public boolean passwordMatch(long userId, String password) {
		Utilizador user = find(userId);
		String pass = Encript.encript(password);
		if (user.getPassword().equals(pass))
			return true;
		else
			return false;
	}

}
