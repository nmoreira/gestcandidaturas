package pt.uc.dei.aor.pfinal.gestaocandidaturas.facades;

import java.util.Collection;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidato;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidatura;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Posicao;

/**
 * Session Bean implementation class AdministradorFacade
 */
@Stateless()
@LocalBean
public class CandidaturaFacade implements ICandidaturaFacade {

	@PersistenceContext(unitName = "gestcandidaturas")
	private EntityManager em;

	@Inject
	private CandidatoFacade candidatoF;

	@Inject
	private PosicaoFacade posicaoF;

	/**
	 * Default constructor.
	 */
	public CandidaturaFacade() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Candidatura create(Candidatura entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public Candidatura update(Candidatura entity) {
		return em.merge(entity);
	}

	@Override
	public boolean delete(Candidatura entity) {
		if (em.find(Candidatura.class, entity.getId()) == null) {
			return false;
		} else {

			try {
				Candidatura entityToBeRemoved = em.merge(entity);
				em.remove(entityToBeRemoved);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}

	@Override
	public Candidatura find(long pk) {
		TypedQuery<Candidatura> q = em.createNamedQuery("Candidatura.findById",
				Candidatura.class).setParameter("id", pk);
		if (!q.getResultList().isEmpty())
			return q.getResultList().get(0);
		else
			return null;
	}

	@Override
	public Collection<Candidatura> findAll() {
		TypedQuery<Candidatura> q = em.createNamedQuery("Candidatura.findAll",
				Candidatura.class);
		return q.getResultList();
	}

	@Override
	public List<Candidatura> findByCandidato(long candidatoId) {
		Candidato cand = candidatoF.find(candidatoId);
		if (cand == null) {
			return null;
		} else {
			TypedQuery<Candidatura> q = em.createNamedQuery(
					"Candidatura.findByCandidato", Candidatura.class)
					.setParameter("candidato", cand);
			return q.getResultList();
		}
	}

	@Override
	public List<Candidatura> findByPosicao(long posicaoId) {
		Posicao pos = posicaoF.find(posicaoId);
		if (pos == null) {
			return null;
		} else {
			TypedQuery<Candidatura> q = em.createNamedQuery(
					"Candidatura.findByPosicao", Candidatura.class)
					.setParameter("candidato", pos);
			return q.getResultList();
		}
	}
}
