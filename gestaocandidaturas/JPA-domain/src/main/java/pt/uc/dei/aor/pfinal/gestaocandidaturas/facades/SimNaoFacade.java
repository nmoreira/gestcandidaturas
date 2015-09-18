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

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.SimNao;

/**
 * Session Bean implementation class SimNao
 */
@Stateless
@LocalBean
public class SimNaoFacade implements ISimNaoFacade {

	@PersistenceContext(unitName = "gestcandidaturas")
	private EntityManager em;

	static Logger logger = LoggerFactory.getLogger(SimNaoFacade.class);

	/**
	 * Default constructor.
	 */
	public SimNaoFacade() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public SimNao create(SimNao entity) {
		try {
			em.persist(entity);
			logger.info("Questao de SimNao criada com sucesso na base de dados");
			return entity;
		} catch (Exception e) {
			logger.error("Erro ao criar a Questao de SimNao na base de dados");
			return null;
		}
	}

	@Override
	public SimNao update(SimNao entity) {
		try {
			SimNao g = em.merge(entity);
			logger.info("Questao de SimNao " + entity.getId()
					+ " atualizada com sucesso");
			return g;
		} catch (Exception e) {
			logger.error("Erro ao atualizar a questao de SimNao "
					+ entity.getId() + " na base de dados");
			return null;
		}
	}

	@Override
	public boolean delete(SimNao entity) {
		if (em.find(SimNao.class, entity.getId()) == null) {
			logger.error("Erro ao apagar a questao de SimNao " + entity.getId()
					+ ". Questao não encontrada");
			return false;
		} else {

			try {
				SimNao entityToBeRemoved = em.merge(entity);
				em.remove(entityToBeRemoved);
				em.flush();
				logger.info("Questao de SimNao " + entity.getId()
						+ " apagada com sucesso da base de dados");
				return true;
			} catch (Exception e) {
				logger.error("Erro ao apagar a questao de SimNao "
						+ entity.getId());
				return false;
			}
		}
	}

	@Override
	public SimNao find(long pk) {
		TypedQuery<SimNao> q = em.createNamedQuery("SimNao.findById",
				SimNao.class).setParameter("id", pk);
		List<SimNao> res = q.getResultList();
		if (!res.isEmpty()) {
			return res.get(0);
		} else
			return null;
	}

	@Override
	public Collection<SimNao> findAll() {
		TypedQuery<SimNao> q = em.createNamedQuery("SimNao.findAll",
				SimNao.class);
		return q.getResultList();
	}

}
