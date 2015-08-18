package pt.uc.dei.aor.pfinal.gestaocandidaturas.facades;

import java.util.Collection;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Gestor;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Perfil;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.Encript;

/**
 * Session Bean implementation class AdministradorFacade
 */
@Stateless()
@LocalBean
public class GestorFacade implements IGestorFacade {

	@PersistenceContext(unitName = "gestcandidaturas")
	private EntityManager em;

	static Logger logger = LoggerFactory.getLogger(GestorFacade.class);

	@Inject
	private PerfilFacade pf;

	/**
	 * Default constructor.
	 */
	public GestorFacade() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Gestor create(Gestor entity) {
		entity.setPassword(Encript.encript(entity.getPassword()));
		String login = entity.getLogin();
		try {
			em.persist(entity);
			Gestor newUser = findByLogin(login);
			logger.info("Gestor " + login
					+ " criado com sucesso na base de dados");
			return newUser;
		} catch (Exception e) {
			logger.error("Erro ao criar o gestor " + login
					+ " na base de dados");
			return null;
		}
	}

	@Override
	public Gestor update(Gestor entity) {
		try {
			Gestor updatedUser = em.merge(entity);
			logger.info("Gestor " + entity.getLogin()
					+ " atualizado com sucesso");
			return updatedUser;
		} catch (Exception e) {
			logger.error("Erro ao atualizar o gestor " + entity.getLogin()
					+ " na base de dados");
			return null;
		}
	}

	@Override
	public boolean delete(Gestor entity) {
		if (em.find(Gestor.class, entity.getId()) == null) {
			logger.error("Erro ao apagar o gestor " + entity.getLogin()
					+ ". Gestor não encontrado");
			return false;
		} else {
			try {
				Gestor entityToBeRemoved = em.merge(entity);
				em.remove(entityToBeRemoved);
				em.flush();
				logger.info("Gestor " + entity.getLogin()
						+ " apagado com sucesso da base de dados");
				return true;
			} catch (Exception e) {
				logger.error("Erro ao apagar o gestor " + entity.getLogin());
				return false;
			}
		}
	}

	@Override
	public Gestor find(long pk) {
		TypedQuery<Gestor> q = em.createNamedQuery("Gestor.findById",
				Gestor.class).setParameter("id", pk);
		if (!q.getResultList().isEmpty())
			return q.getResultList().get(0);
		else
			return null;
	}

	@Override
	public Collection<Gestor> findAll() {
		TypedQuery<Gestor> q = em.createNamedQuery("Gestor.findAll",
				Gestor.class);
		return q.getResultList();
	}

	@Override
	public Gestor findByLogin(String login) {
		TypedQuery<Gestor> q = em.createNamedQuery("Gestor.findByLogin",
				Gestor.class).setParameter("login", login);
		if (!q.getResultList().isEmpty())
			return q.getResultList().get(0);
		else
			return null;
	}

	@Override
	public Gestor findByEmail(String email) {
		TypedQuery<Gestor> q = em.createNamedQuery("Gestor.findByLogin",
				Gestor.class).setParameter("email", email);
		if (!q.getResultList().isEmpty())
			return q.getResultList().get(0);
		else
			return null;
	}

	@Override
	public Perfil getPerfilGestor() {
		Perfil gest = pf.findByName("GESTOR");
		if (gest == null) {
			pf.create(new Perfil("GESTOR"));
			gest = pf.findByName("GESTOR");
			return gest;
		} else {
			return gest;
		}
	}

	@Override
	public void changePassword(long userId, String newPassword) {
		Gestor user = find(userId);
		user.setPassword(Encript.encript(newPassword));
		update(user);
		logger.info("Password do gestor " + user.getLogin()
				+ " atualizada com sucesso");
	}

	@Override
	public Gestor createBypassingPassword(Gestor gestor) {
		String login = gestor.getLogin();
		try {
			em.persist(gestor);
			Gestor newUser = findByLogin(login);
			logger.info("Gestor " + login
					+ " criado com sucesso na base de dados");
			return newUser;
		} catch (Exception e) {
			logger.error("Erro ao criar o gestor " + login
					+ " na base de dados");
			return null;
		}
	}

}
