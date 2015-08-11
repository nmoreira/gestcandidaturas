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
		em.persist(entity);
		return entity;
	}

	@Override
	public Utilizador update(Utilizador entity) {
		return em.merge(entity);
	}

	@Override
	public boolean delete(Utilizador entity) {
		if (em.find(Utilizador.class, entity.getId()) == null) {
			return false;
		} else {

			try {
				Utilizador entityToBeRemoved = em.merge(entity);
				em.remove(entityToBeRemoved);
				em.flush();
				return true;
			} catch (Exception e) {
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
	public void changePassword(long userId, String newPassword) {
		Utilizador user = find(userId);
		user.setPassword(Encript.encript(newPassword));
		update(user);
	}

	@Override
	public List<Utilizador> getUtilizadoresSemPerfil() {
		List<Utilizador> listaFinal = new ArrayList<>();
		q = em.createQuery("from Utilizador u where u.perfil = null");
		listaFinal = q.getResultList();
		return listaFinal;
	}

}
