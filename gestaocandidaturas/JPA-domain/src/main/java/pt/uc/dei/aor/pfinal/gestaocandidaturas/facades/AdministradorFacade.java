package pt.uc.dei.aor.pfinal.gestaocandidaturas.facades;

import java.util.Collection;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
		em.persist(entity);
		return entity;
	}

	@Override
	public Administrador update(Administrador entity) {
		return em.merge(entity);
	}

	@Override
	public void delete(Administrador entity) {
		Administrador entityToBeRemoved = em.merge(entity);
		em.remove(entityToBeRemoved);
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
	}

}
