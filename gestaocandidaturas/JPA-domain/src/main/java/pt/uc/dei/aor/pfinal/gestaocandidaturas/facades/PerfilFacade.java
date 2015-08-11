package pt.uc.dei.aor.pfinal.gestaocandidaturas.facades;

import java.util.Collection;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Perfil;

/**
 * Session Bean implementation class PerfilFacade
 */
@Stateless
@LocalBean
public class PerfilFacade implements IPerfilFacade {

	@PersistenceContext(unitName = "gestcandidaturas")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public PerfilFacade() {
	}

	@Override
	public Perfil findByName(String nomePerfil) {
		// findByName
		TypedQuery<Perfil> q = em.createNamedQuery("Perfil.findByName",
				Perfil.class).setParameter("cargo", nomePerfil);

		List<Perfil> res = q.getResultList();
		if (!res.isEmpty()) {
			return res.get(0);
		} else
			return null;
	}

	@Override
	public Perfil create(Perfil entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public Perfil update(Perfil entity) {
		return em.merge(entity);
	}

	@Override
	public boolean delete(Perfil entity) {
		if (em.find(Perfil.class, entity.getId()) == null) {
			return false;
		} else {

			try {
				Perfil entityToBeRemoved = em.merge(entity);
				em.remove(entityToBeRemoved);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}

	@Override
	public Perfil find(long pk) {
		TypedQuery<Perfil> q = em.createNamedQuery("Perfil.findById",
				Perfil.class).setParameter("id", pk);
		List<Perfil> res = q.getResultList();
		if (!res.isEmpty()) {
			return res.get(0);
		} else
			return null;
	}

	@Override
	public Collection<Perfil> findAll() {
		TypedQuery<Perfil> q = em.createNamedQuery("Perfil.findAll",
				Perfil.class);
		return q.getResultList();
	}

}
