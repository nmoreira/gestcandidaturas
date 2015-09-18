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

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Escala1a5;

/**
 * Session Bean implementation class GuiaoFacade
 */
@Stateless
@LocalBean
public class Escala1a5Facade implements IEscala1a5Facade {

	@PersistenceContext(unitName = "gestcandidaturas")
	private EntityManager em;

	static Logger logger = LoggerFactory.getLogger(Escala1a5Facade.class);

	/**
	 * Default constructor.
	 */
	public Escala1a5Facade() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Escala1a5 create(Escala1a5 entity) {
		try {
			em.persist(entity);
			logger.info("Questao de Escala1a5 criada com sucesso na base de dados");
			return entity;
		} catch (Exception e) {
			logger.error("Erro ao criar a Questao de Escala1a5 na base de dados");
			return null;
		}
	}

	@Override
	public Escala1a5 update(Escala1a5 entity) {
		try {
			Escala1a5 g = em.merge(entity);
			logger.info("Questao de Escala1a5 " + entity.getId()
					+ " atualizada com sucesso");
			return g;
		} catch (Exception e) {
			logger.error("Erro ao atualizar a questao de Escala1a5 "
					+ entity.getId() + " na base de dados");
			return null;
		}
	}

	@Override
	public boolean delete(Escala1a5 entity) {
		if (em.find(Escala1a5.class, entity.getId()) == null) {
			logger.error("Erro ao apagar a questao de Escala1a5 "
					+ entity.getId() + ". Questao não encontrada");
			return false;
		} else {

			try {
				Escala1a5 entityToBeRemoved = em.merge(entity);
				em.remove(entityToBeRemoved);
				em.flush();
				logger.info("Questao de Escala1a5 " + entity.getId()
						+ " apagada com sucesso da base de dados");
				return true;
			} catch (Exception e) {
				logger.error("Erro ao apagar a questao de Escala1a5 "
						+ entity.getId());
				return false;
			}
		}
	}

	@Override
	public Escala1a5 find(long pk) {
		TypedQuery<Escala1a5> q = em.createNamedQuery("Escala1a5.findById",
				Escala1a5.class).setParameter("id", pk);
		List<Escala1a5> res = q.getResultList();
		if (!res.isEmpty()) {
			return res.get(0);
		} else
			return null;
	}

	@Override
	public Collection<Escala1a5> findAll() {
		TypedQuery<Escala1a5> q = em.createNamedQuery("Escala1a5.findAll",
				Escala1a5.class);
		return q.getResultList();
	}

}
