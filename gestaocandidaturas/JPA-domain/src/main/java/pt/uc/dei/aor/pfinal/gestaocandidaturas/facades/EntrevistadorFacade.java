package pt.uc.dei.aor.pfinal.gestaocandidaturas.facades;

import java.util.Collection;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
		em.persist(entity);
		return entity;
	}

	@Override
	public Entrevistador update(Entrevistador entity) {
		return em.merge(entity);
	}

	@Override
	public boolean delete(Entrevistador entity) {
		if (em.find(Entrevistador.class, entity.getId()) == null) {
			return false;
		} else {

			try {
				Entrevistador entityToBeRemoved = em.merge(entity);
				em.remove(entityToBeRemoved);
				return true;
			} catch (Exception e) {
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
	}

}
