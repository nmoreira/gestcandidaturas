package pt.uc.dei.aor.pfinal.gestaocandidaturas.facades;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.GrupoEntrevistadores;

/**
 * Session Bean implementation class AdministradorFacade
 */
@Stateless()
@LocalBean
public class GrupoEntrevistadoresFacade implements IGrupoEntrevistadoresFacade {

	@PersistenceContext(unitName = "gestcandidaturas")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public GrupoEntrevistadoresFacade() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<GrupoEntrevistadores> findAll() {
		TypedQuery<GrupoEntrevistadores> q = em.createNamedQuery(
				"GrupoEntrevistadores.findAll", GrupoEntrevistadores.class);
		return q.getResultList();
	}

	@Override
	public GrupoEntrevistadores findById(long id) {
		TypedQuery<GrupoEntrevistadores> q = em.createNamedQuery(
				"GrupoEntrevistadores.findById", GrupoEntrevistadores.class)
				.setParameter("id", id);
		if (!q.getResultList().isEmpty())
			return q.getResultList().get(0);
		else
			return null;
	}

}
