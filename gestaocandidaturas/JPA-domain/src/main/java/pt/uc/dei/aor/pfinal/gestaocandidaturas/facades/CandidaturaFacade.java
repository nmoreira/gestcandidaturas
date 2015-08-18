package pt.uc.dei.aor.pfinal.gestaocandidaturas.facades;

import java.util.Collection;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	static Logger logger = LoggerFactory.getLogger(CandidaturaFacade.class);

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
		String cand = entity.getCandidato().getLogin();
		String pos = entity.getPosicao().getCodPosicao();
		try {
			em.persist(entity);
			Candidatura candidatura = findByCandidatoAndPosicao(entity
					.getCandidato().getId(), entity.getPosicao().getId());
			logger.info("Candidatura à posição " + pos + " do candidato "
					+ cand + " criada com sucesso na base de dados");
			return candidatura;
		} catch (Exception e) {
			logger.error("Erro ao criar a candidatura à posicao " + pos
					+ " do candidato " + cand + " na base de dados");
			return null;
		}
	}

	@Override
	public Candidatura update(Candidatura entity) {
		try {
			Candidatura updatedPos = em.merge(entity);
			logger.info("Candidatura " + entity.getId()
					+ " atualizada com sucesso");
			return updatedPos;
		} catch (Exception e) {
			logger.error("Erro ao atualizar a Candidatura " + entity.getId()
					+ " na base de dados");
			return null;
		}
	}

	@Override
	public boolean delete(Candidatura entity) {
		if (em.find(Candidatura.class, entity.getId()) == null) {
			logger.error("Erro ao apagar a candidatura " + entity.getId()
					+ ". Candidatura não encontrada");
			return false;
		} else {
			try {
				Candidatura entityToBeRemoved = em.merge(entity);
				em.remove(entityToBeRemoved);
				em.flush();
				logger.info("Candidatura " + entity.getId()
						+ " apagada com sucesso da base de dados");
				return true;
			} catch (Exception e) {
				logger.error("Erro ao apagar a Candidatura " + entity.getId());
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
					.setParameter("posicao", pos);
			return q.getResultList();
		}
	}

	@Override
	public Candidatura findByCandidatoAndPosicao(long candidatoId,
			long posicaoId) {
		Candidato cand = candidatoF.find(candidatoId);
		Posicao pos = posicaoF.find(posicaoId);
		if (cand == null || pos == null)
			return null;
		else {
			TypedQuery<Candidatura> q = em
					.createNamedQuery("Candidatura.findByCandidatoAndPosicao",
							Candidatura.class).setParameter("candidato", cand)
					.setParameter("posicao", pos);
			return q.getSingleResult();
		}
	}
}
