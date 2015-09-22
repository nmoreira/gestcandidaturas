package pt.uc.dei.aor.pfinal.gestaocandidaturas.facades;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidatura;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Entrevista;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.GrupoEntrevistadores;

/**
 * Session Bean implementation class EntrevistaFacade
 */
@Stateless()
@LocalBean
public class EntrevistaFacade implements IEntrevistaFacade {

	@PersistenceContext(unitName = "gestcandidaturas")
	private EntityManager em;

	static Logger logger = LoggerFactory.getLogger(EntrevistaFacade.class);

	private Query q;

	@Inject
	private CandidaturaFacade candidaturaF;

	@Inject
	private GrupoEntrevistadoresFacade entrevistadorF;

	/**
	 * Default constructor.
	 */
	public EntrevistaFacade() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Entrevista create(Entrevista entity) {

		try {
			em.persist(entity);
			logger.info("Entrevista à candidatura id:"
					+ entity.getCandidatura().getId() + " pelo entrevistador "
					+ entity.getEntrevistador().getNome()
					+ " criada com sucesso na base de dados");
			return entity;
		} catch (Exception e) {
			logger.error("Erro ao criar a entrevista à candidatura id:"
					+ entity.getCandidatura().getId() + " pelo entrevistador "
					+ entity.getEntrevistador().getNome() + " na base de dados");
			return null;
		}
	}

	@Override
	public Entrevista update(Entrevista entity) {
		try {
			Entrevista updatedEnt = em.merge(entity);
			logger.info("Entrevista " + entity.getId()
					+ " atualizada com sucesso");
			return updatedEnt;
		} catch (Exception e) {
			logger.error("Erro ao atualizar a Entrevista " + entity.getId()
					+ " na base de dados");
			return null;
		}
	}

	@Override
	public boolean delete(Entrevista entity) {
		if (em.find(Entrevista.class, entity.getId()) == null) {
			logger.error("Erro ao apagar a entrevista " + entity.getId()
					+ ". Entrevista não encontrada");
			return false;
		} else {
			try {
				Entrevista entityToBeRemoved = em.merge(entity);
				em.remove(entityToBeRemoved);
				em.flush();
				logger.info("Entrevista " + entity.getId()
						+ " apagada com sucesso da base de dados");
				return true;
			} catch (Exception e) {
				logger.error("Erro ao apagar a Entrevista " + entity.getId());
				return false;
			}
		}
	}

	@Override
	public Entrevista find(long pk) {
		TypedQuery<Entrevista> q = em.createNamedQuery("Entrevista.findById",
				Entrevista.class).setParameter("id", pk);
		if (!q.getResultList().isEmpty())
			return q.getResultList().get(0);
		else
			return null;
	}

	@Override
	public Collection<Entrevista> findAll() {
		TypedQuery<Entrevista> q = em.createNamedQuery("Entrevista.findAll",
				Entrevista.class);
		return q.getResultList();
	}

	@Override
	public List<Entrevista> findByCandidatura(long candidaturaId) {
		Candidatura cand = candidaturaF.find(candidaturaId);
		if (cand == null) {
			return null;
		} else {
			TypedQuery<Entrevista> q = em.createNamedQuery(
					"Entrevista.findByCandidatura", Entrevista.class)
					.setParameter("candidatura", cand);
			return q.getResultList();
		}
	}

	@Override
	public List<Entrevista> findByEntrevistador(long entrevistadorId) {
		GrupoEntrevistadores ent = entrevistadorF.findById(entrevistadorId);
		if (ent == null) {
			return null;
		} else {
			TypedQuery<Entrevista> q = em.createNamedQuery(
					"Entrevista.findByEntrevistador", Entrevista.class)
					.setParameter("entrevistador", ent);
			return q.getResultList();
		}
	}

	@Override
	public List<Entrevista> findByData(Date dataEntrevista) {
		if (dataEntrevista != null) {
			TypedQuery<Entrevista> q = em.createNamedQuery(
					"Entrevista.findByData", Entrevista.class).setParameter(
					"data", dataEntrevista);
			return q.getResultList();
		} else
			return null;
	}

}
