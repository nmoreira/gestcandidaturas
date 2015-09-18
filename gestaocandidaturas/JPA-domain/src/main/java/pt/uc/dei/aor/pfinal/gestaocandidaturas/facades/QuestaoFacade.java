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

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Questao;

/**
 * Session Bean implementation class GuiaoFacade
 */
@Stateless
@LocalBean
public class QuestaoFacade implements IQuestaoFacade {

	@PersistenceContext(unitName = "gestcandidaturas")
	private EntityManager em;

	static Logger logger = LoggerFactory.getLogger(QuestaoFacade.class);

	/**
	 * Default constructor.
	 */
	public QuestaoFacade() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Questao create(Questao entity) {
		try {
			em.persist(entity);
			logger.info("Questao criada com sucesso na base de dados");
			return entity;
		} catch (Exception e) {
			logger.error("Erro ao criar a Questao na base de dados");
			return null;
		}
	}

	@Override
	public Questao update(Questao entity) {
		try {
			Questao g = em.merge(entity);
			logger.info("Questao " + entity.getId() + " atualizada com sucesso");
			return g;
		} catch (Exception e) {
			logger.error("Erro ao atualizar a questao " + entity.getId()
					+ " na base de dados");
			return null;
		}
	}

	@Override
	public boolean delete(Questao entity) {
		if (em.find(Questao.class, entity.getId()) == null) {
			logger.error("Erro ao apagar a questao " + entity.getId()
					+ ". Questao não encontrada");
			return false;
		} else {

			try {
				Questao entityToBeRemoved = em.merge(entity);
				em.remove(entityToBeRemoved);
				em.flush();
				logger.info("Questao " + entity.getId()
						+ " apagada com sucesso da base de dados");
				return true;
			} catch (Exception e) {
				logger.error("Erro ao apagar a questao " + entity.getId());
				return false;
			}
		}
	}

	@Override
	public Questao find(long pk) {
		TypedQuery<Questao> q = em.createNamedQuery("Questao.findById",
				Questao.class).setParameter("id", pk);
		List<Questao> res = q.getResultList();
		if (!res.isEmpty()) {
			return res.get(0);
		} else
			return null;
	}

	@Override
	public Collection<Questao> findAll() {
		TypedQuery<Questao> q = em.createNamedQuery("Questao.findAll",
				Questao.class);
		return q.getResultList();
	}

}
