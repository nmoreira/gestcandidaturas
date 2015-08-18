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

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Administrador;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Perfil;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.Encript;

/**
 * Session Bean implementation class AdministradorFacade
 */
@Stateless()
@LocalBean
public class AdministradorFacade implements IAdministradorFacade {

	@PersistenceContext(unitName = "gestcandidaturas")
	private EntityManager em;

	static Logger logger = LoggerFactory.getLogger(AdministradorFacade.class);

	@Inject
	private PerfilFacade pf;

	/**
	 * Default constructor.
	 */
	public AdministradorFacade() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Administrador create(Administrador entity) {
		entity.setPassword(Encript.encript(entity.getPassword()));
		String login = entity.getLogin();
		try {
			em.persist(entity);
			Administrador newUser = findByLogin(login);
			logger.info("Administrador " + login
					+ " criado com sucesso na base de dados");
			return newUser;
		} catch (Exception e) {
			logger.error("Erro ao criar o administrador " + login
					+ " na base de dados");
			return null;
		}
	}

	@Override
	public Administrador update(Administrador entity) {
		try {
			Administrador updatedUser = em.merge(entity);
			logger.info("Administrador " + entity.getLogin()
					+ " atualizado com sucesso");
			return updatedUser;
		} catch (Exception e) {
			logger.error("Erro ao atualizar o administrador "
					+ entity.getLogin() + " na base de dados");
			return null;
		}
	}

	@Override
	public boolean delete(Administrador entity) {
		if (em.find(Administrador.class, entity.getId()) == null) {
			logger.error("Erro ao apagar o administrador " + entity.getLogin()
					+ ". Administrador não encontrado");
			return false;
		} else {
			try {
				Administrador entityToBeRemoved = em.merge(entity);
				em.remove(entityToBeRemoved);
				em.flush();
				logger.info("Administrador " + entity.getLogin()
						+ " apagado com sucesso da base de dados");
				return true;
			} catch (Exception e) {
				logger.error("Erro ao apagar o administrador "
						+ entity.getLogin());
				return false;
			}
		}
	}

	@Override
	public Administrador find(long pk) {
		TypedQuery<Administrador> q = em.createNamedQuery(
				"Administrador.findById", Administrador.class).setParameter(
				"id", pk);
		if (!q.getResultList().isEmpty())
			return q.getResultList().get(0);
		else
			return null;
	}

	@Override
	public Collection<Administrador> findAll() {
		TypedQuery<Administrador> q = em.createNamedQuery(
				"Administrador.findAll", Administrador.class);
		return q.getResultList();
	}

	@Override
	public Administrador findByLogin(String login) {
		TypedQuery<Administrador> q = em.createNamedQuery(
				"Administrador.findByLogin", Administrador.class).setParameter(
				"login", login);
		if (!q.getResultList().isEmpty())
			return q.getResultList().get(0);
		else
			return null;
	}

	@Override
	public Administrador findByEmail(String email) {
		TypedQuery<Administrador> q = em.createNamedQuery(
				"Administrador.findByLogin", Administrador.class).setParameter(
				"email", email);
		if (!q.getResultList().isEmpty())
			return q.getResultList().get(0);
		else
			return null;
	}

	@Override
	public Perfil getPerfilAdmin() {
		Perfil admin = pf.findByName("ADMIN");
		if (admin == null) {
			pf.create(new Perfil("ADMIN"));
			admin = pf.findByName("ADMIN");
			return admin;
		} else {
			return admin;
		}
	}

	@Override
	public void changePassword(long userId, String newPassword) {
		Administrador user = find(userId);
		user.setPassword(Encript.encript(newPassword));
		update(user);
		logger.info("Password do administrador " + user.getLogin()
				+ " atualizada com sucesso");
	}

	@Override
	public Administrador createBypassingPassword(Administrador newAdmin) {
		String login = newAdmin.getLogin();
		try {
			em.persist(newAdmin);
			Administrador newUser = findByLogin(login);
			logger.info("Administrador " + login
					+ " criado com sucesso na base de dados");
			return newUser;
		} catch (Exception e) {
			logger.error("Erro ao criar o administrador " + login
					+ " na base de dados");
			return null;
		}
	}

}
