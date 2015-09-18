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

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.TextoLivre;

/**
 * Session Bean implementation class TextoLivreFacade
 */
@Stateless
@LocalBean
public class TextoLivreFacade implements ITextoLivreFacade {

	@PersistenceContext(unitName = "gestcandidaturas")
	private EntityManager em;

	static Logger logger = LoggerFactory.getLogger(TextoLivreFacade.class);

	/**
	 * Default constructor.
	 */
	public TextoLivreFacade() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public TextoLivre create(TextoLivre entity) {
		try {
			em.persist(entity);
			logger.info("Questao de TextoLivre criada com sucesso na base de dados");
			return entity;
		} catch (Exception e) {
			logger.error("Erro ao criar a Questao de TextoLivre na base de dados");
			return null;
		}
	}

	@Override
	public TextoLivre update(TextoLivre entity) {
		try {
			TextoLivre g = em.merge(entity);
			logger.info("Questao de TextoLivre " + entity.getId()
					+ " atualizada com sucesso");
			return g;
		} catch (Exception e) {
			logger.error("Erro ao atualizar a questao de TextoLivre "
					+ entity.getId() + " na base de dados");
			return null;
		}
	}

	@Override
	public boolean delete(TextoLivre entity) {
		if (em.find(TextoLivre.class, entity.getId()) == null) {
			logger.error("Erro ao apagar a questao de TextoLivre "
					+ entity.getId() + ". Questao não encontrada");
			return false;
		} else {

			try {
				TextoLivre entityToBeRemoved = em.merge(entity);
				em.remove(entityToBeRemoved);
				em.flush();
				logger.info("Questao de TextoLivre " + entity.getId()
						+ " apagada com sucesso da base de dados");
				return true;
			} catch (Exception e) {
				logger.error("Erro ao apagar a questao de TextoLivre "
						+ entity.getId());
				return false;
			}
		}
	}

	@Override
	public TextoLivre find(long pk) {
		TypedQuery<TextoLivre> q = em.createNamedQuery("TextoLivre.findById",
				TextoLivre.class).setParameter("id", pk);
		List<TextoLivre> res = q.getResultList();
		if (!res.isEmpty()) {
			return res.get(0);
		} else
			return null;
	}

	@Override
	public Collection<TextoLivre> findAll() {
		TypedQuery<TextoLivre> q = em.createNamedQuery("TextoLivre.findAll",
				TextoLivre.class);
		return q.getResultList();
	}

}
