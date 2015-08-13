package pt.uc.dei.aor.pfinal.gestaocandidaturas.facades;

import java.util.Collection;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
		em.persist(entity);
		return entity;
	}

	@Override
	public Gestor update(Gestor entity) {
		return em.merge(entity);
	}

	@Override
	public boolean delete(Gestor entity) {
		if (em.find(Gestor.class, entity.getId()) == null) {
			return false;
		} else {

			try {
				Gestor entityToBeRemoved = em.merge(entity);
				em.remove(entityToBeRemoved);
				return true;
			} catch (Exception e) {
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
	}

	@Override
	public Gestor createBypassingPassword(Gestor gestor) {
		em.persist(gestor);
		return gestor;
	}

}
