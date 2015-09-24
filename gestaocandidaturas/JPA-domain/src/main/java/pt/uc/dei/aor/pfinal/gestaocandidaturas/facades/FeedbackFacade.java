package pt.uc.dei.aor.pfinal.gestaocandidaturas.facades;

import java.util.Collection;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Entrevista;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Feedback;

/**
 * Session Bean implementation class GuiaoFacade
 */
@Stateless
@LocalBean
public class FeedbackFacade implements IFeedbackFacade {

	@PersistenceContext(unitName = "gestcandidaturas")
	private EntityManager em;

	static Logger logger = LoggerFactory.getLogger(FeedbackFacade.class);

	/**
	 * Default constructor.
	 */
	public FeedbackFacade() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Feedback create(Feedback entity) {
		try {
			em.persist(entity);
			logger.info("Feedback criado com sucesso na base de dados");
			return entity;
		} catch (Exception e) {
			logger.error("Erro ao criar o Feedback na base de dados");
			return null;
		}
	}

	@Override
	public Feedback update(Feedback entity) {
		try {
			Feedback f = em.merge(entity);
			logger.info("Feedback " + entity.getId()
					+ " atualizado com sucesso");
			return f;
		} catch (Exception e) {
			logger.error("Erro ao atualizar o feedback " + entity.getId()
					+ " na base de dados");
			return null;
		}
	}

	@Override
	public boolean delete(Feedback entity) {
		if (em.find(Feedback.class, entity.getId()) == null) {
			logger.error("Erro ao apagar o feedback " + entity.getId()
					+ ". Feedback não encontrado");
			return false;
		} else {

			try {
				Feedback entityToBeRemoved = em.merge(entity);
				em.remove(entityToBeRemoved);
				em.flush();
				logger.info("Feedback " + entity.getId()
						+ " apagado com sucesso da base de dados");
				return true;
			} catch (Exception e) {
				logger.error("Erro ao apagar o feedback " + entity.getId());
				return false;
			}
		}
	}

	@Override
	public Feedback find(long pk) {
		TypedQuery<Feedback> q = em.createNamedQuery("Feedback.findById",
				Feedback.class).setParameter("id", pk);
		List<Feedback> res = q.getResultList();
		if (!res.isEmpty()) {
			return res.get(0);
		} else
			return null;
	}

	@Override
	public Collection<Feedback> findAll() {
		TypedQuery<Feedback> q = em.createNamedQuery("Feedback.findAll",
				Feedback.class);
		return q.getResultList();
	}

	@Override
	public Feedback getFeedBackByEntrevista(Entrevista entrevista) {
		TypedQuery<Feedback> q = em.createNamedQuery(
				"Feedback.findByEntrevista", Feedback.class).setParameter(
				"entrevista", entrevista);
		return q.getSingleResult();
	}

}
