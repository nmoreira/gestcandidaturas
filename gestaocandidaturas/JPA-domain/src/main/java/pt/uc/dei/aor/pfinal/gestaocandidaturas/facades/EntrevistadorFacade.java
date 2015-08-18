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

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Entrevistador;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Perfil;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.Encript;

/**
 * Session Bean implementation class AdministradorFacade
 */
@Stateless()
@LocalBean
public class EntrevistadorFacade implements IEntrevistadorFacade {

	@PersistenceContext(unitName = "gestcandidaturas")
	private EntityManager em;

	static Logger logger = LoggerFactory.getLogger(EntrevistadorFacade.class);

	@Inject
	private PerfilFacade pf;

	/**
	 * Default constructor.
	 */
	public EntrevistadorFacade() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Entrevistador create(Entrevistador entity) {
		entity.setPassword(Encript.encript(entity.getPassword()));
		String login = entity.getLogin();
		try {
			em.persist(entity);
			Entrevistador newUser = findByLogin(login);
			logger.info("Entrevistador " + login
					+ " criado com sucesso na base de dados");
			return newUser;
		} catch (Exception e) {
			logger.error("Erro ao criar o entrevistador " + login
					+ " na base de dados");
			return null;
		}
	}

	@Override
	public Entrevistador update(Entrevistador entity) {
		try {
			Entrevistador updatedUser = em.merge(entity);
			logger.info("Entrevistador " + entity.getLogin()
					+ " atualizado com sucesso");
			return updatedUser;
		} catch (Exception e) {
			logger.error("Erro ao atualizar o entrevistador "
					+ entity.getLogin() + " na base de dados");
			return null;
		}
	}

	@Override
	public boolean delete(Entrevistador entity) {
		if (em.find(Entrevistador.class, entity.getId()) == null) {
			logger.error("Erro ao apagar o entrevistador " + entity.getLogin()
					+ ". Entrevistador não encontrado");
			return false;
		} else {
			try {
				Entrevistador entityToBeRemoved = em.merge(entity);
				em.remove(entityToBeRemoved);
				em.flush();
				logger.info("Entrevistador " + entity.getLogin()
						+ " apagado com sucesso da base de dados");
				return true;
			} catch (Exception e) {
				logger.error("Erro ao apagar o entrevistador "
						+ entity.getLogin());
				return false;
			}
		}
	}

	@Override
	public Entrevistador find(long pk) {
		TypedQuery<Entrevistador> q = em.createNamedQuery(
				"Entrevistador.findById", Entrevistador.class).setParameter(
				"id", pk);
		if (!q.getResultList().isEmpty())
			return q.getResultList().get(0);
		else
			return null;
	}

	@Override
	public Collection<Entrevistador> findAll() {
		TypedQuery<Entrevistador> q = em.createNamedQuery(
				"Entrevistador.findAll", Entrevistador.class);
		return q.getResultList();
	}

	@Override
	public Entrevistador findByLogin(String login) {
		TypedQuery<Entrevistador> q = em.createNamedQuery(
				"Entrevistador.findByLogin", Entrevistador.class).setParameter(
				"login", login);
		if (!q.getResultList().isEmpty())
			return q.getResultList().get(0);
		else
			return null;
	}

	@Override
	public Entrevistador findByEmail(String email) {
		TypedQuery<Entrevistador> q = em.createNamedQuery(
				"Entrevistador.findByLogin", Entrevistador.class).setParameter(
				"email", email);
		if (!q.getResultList().isEmpty())
			return q.getResultList().get(0);
		else
			return null;
	}

	@Override
	public Perfil getPerfilEntrevistador() {
		Perfil ent = pf.findByName("ENTREVISTADOR");
		if (ent == null) {
			pf.create(new Perfil("ENTREVISTADOR"));
			ent = pf.findByName("ENTREVISTADOR");
			return ent;
		} else {
			return ent;
		}
	}

	@Override
	public void changePassword(long userId, String newPassword) {
		Entrevistador user = find(userId);
		user.setPassword(Encript.encript(newPassword));
		update(user);
		logger.info("Password do entrevistador " + user.getLogin()
				+ " atualizada com sucesso");
	}

	@Override
	public Entrevistador createBypassingPassword(Entrevistador entrevistador) {
		String login = entrevistador.getLogin();
		try {
			em.persist(entrevistador);
			Entrevistador newUser = findByLogin(login);
			logger.info("Entrevistador " + login
					+ " criado com sucesso na base de dados");
			return newUser;
		} catch (Exception e) {
			logger.error("Erro ao criar o entrevistador " + login
					+ " na base de dados");
			return null;
		}
	}

}
