package pt.uc.dei.aor.pfinal.gestaocandidaturas.facades;

import java.util.Collection;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Guiao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Questao;

/**
 * Session Bean implementation class GuiaoFacade
 */
@Stateless
@LocalBean
public class GuiaoFacade implements IGuiaoFacade {

	@PersistenceContext(unitName = "gestcandidaturas")
	private EntityManager em;

	static Logger logger = LoggerFactory.getLogger(GuiaoFacade.class);

	private Query q;

	/**
	 * Default constructor.
	 */
	public GuiaoFacade() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Guiao create(Guiao entity) {
		try {
			em.persist(entity);
			logger.info("Guião criado com sucesso na base de dados");
			return entity;
		} catch (Exception e) {
			logger.error("Erro ao criar o Guião na base de dados");
			return null;
		}
	}

	@Override
	public Guiao update(Guiao entity) {
		try {
			Guiao g = em.merge(entity);
			logger.info("Guião " + entity.getId() + " atualizado com sucesso");
			return g;
		} catch (Exception e) {
			logger.error("Erro ao atualizar o guião " + entity.getId()
					+ " na base de dados");
			return null;
		}
	}

	@Override
	public boolean delete(Guiao entity) {
		if (em.find(Guiao.class, entity.getId()) == null) {
			logger.error("Erro ao apagar o guião " + entity.getId()
					+ ". Guião não encontrado");
			return false;
		} else {

			try {
				Guiao entityToBeRemoved = em.merge(entity);
				em.remove(entityToBeRemoved);
				em.flush();
				logger.info("Guião " + entity.getId()
						+ " apagado com sucesso da base de dados");
				return true;
			} catch (Exception e) {
				logger.error("Erro ao apagar o guião " + entity.getId());
				return false;
			}
		}
	}

	@Override
	public Guiao find(long pk) {
		TypedQuery<Guiao> q = em
				.createNamedQuery("Guiao.findById", Guiao.class).setParameter(
						"id", pk);
		List<Guiao> res = q.getResultList();
		if (!res.isEmpty()) {
			return res.get(0);
		} else
			return null;
	}

	@Override
	public Collection<Guiao> findAll() {
		TypedQuery<Guiao> q = em.createNamedQuery("Guiao.findAll", Guiao.class);
		return q.getResultList();
	}

	@Override
	public List<Questao> getQuestoesByGuiaoId(long id) {
		q = em.createQuery("from Guiao g left join fetch g.questoes where g.id = :id");
		q.setParameter("id", id);
		Guiao g = (Guiao) q.getSingleResult();
		return g.getQuestoes();
	}

	@Override
	public List<Guiao> getGuioesDisponiveis() {
		q = em.createQuery("from Guiao g where g.disponivel = true");
		return q.getResultList();
	}

	@Override
	public List<Guiao> getGuioesDisponiveisFetchQuestoes() {
		q = em.createQuery("select distinct g from Guiao g left join fetch g.questoes where g.disponivel = true");
		return q.getResultList();
	}

	@Override
	public boolean guiaoEmUso(long guiaoId) {
		q = em.createQuery("select distinct g from Guiao g left join fetch g.posicoes where g.id = :id");
		q.setParameter("id", guiaoId);
		Guiao g = (Guiao) q.getSingleResult();
		if (g.getPosicoes().size() > 0)
			return true;
		else
			return false;
	}

	@Override
	public Guiao getGuiaoByIdFetchQuestoes(long guiaoId) {
		q = em.createQuery("select distinct g from Guiao g left join fetch g.questoes where g.id = :id");
		q.setParameter("id", guiaoId);
		return (Guiao) q.getSingleResult();
	}

}
