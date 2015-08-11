package pt.uc.dei.aor.pfinal.gestaocandidaturas.facades;

import java.util.Collection;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Posicao;

/**
 * Session Bean implementation class AdministradorFacade
 */
@Stateless()
@LocalBean
public class PosicaoFacade implements IPosicaoFacade {

	@PersistenceContext(unitName = "gestcandidaturas")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public PosicaoFacade() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Posicao create(Posicao entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public Posicao update(Posicao entity) {
		return em.merge(entity);
	}

	@Override
	public boolean delete(Posicao entity) {
		if (em.find(Posicao.class, entity.getId()) == null) {
			return false;
		} else {

			try {
				Posicao entityToBeRemoved = em.merge(entity);
				em.remove(entityToBeRemoved);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}

	@Override
	public Posicao find(long pk) {
		TypedQuery<Posicao> q = em.createNamedQuery("Posicao.findById",
				Posicao.class).setParameter("id", pk);
		if (!q.getResultList().isEmpty())
			return q.getResultList().get(0);
		else
			return null;
	}

	@Override
	public Collection<Posicao> findAll() {
		TypedQuery<Posicao> q = em.createNamedQuery("Posicao.findAll",
				Posicao.class);
		return q.getResultList();
	}

	@Override
	public List<Posicao> findByTitulo(String titulo) {
		TypedQuery<Posicao> q = em.createNamedQuery("Posicao.findByTitulo",
				Posicao.class).setParameter("titulo", titulo);
		return q.getResultList();
	}

	@Override
	public List<Posicao> findByEmpresa(String empresa) {
		TypedQuery<Posicao> q = em.createNamedQuery("Posicao.findByEmpresa",
				Posicao.class).setParameter("empresa", empresa);
		return q.getResultList();
	}

	@Override
	public Posicao findByCodPosicao(String codPosicao) {
		TypedQuery<Posicao> q = em.createNamedQuery("Posicao.findByCodPosicao",
				Posicao.class).setParameter("codPosicao", codPosicao);
		if (!q.getResultList().isEmpty())
			return q.getResultList().get(0);
		else
			return null;
	}

}
