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

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Perfil;

/**
 * Session Bean implementation class PerfilFacade
 */
@Stateless
@LocalBean
public class PerfilFacade implements IPerfilFacade {

	@PersistenceContext(unitName = "gestcandidaturas")
	private EntityManager em;

	static Logger logger = LoggerFactory.getLogger(PerfilFacade.class);

	/**
	 * Default constructor.
	 */
	public PerfilFacade() {
	}

	@Override
	public Perfil findByName(String nomePerfil) {
		// findByName
		TypedQuery<Perfil> q = em.createNamedQuery("Perfil.findByName",
				Perfil.class).setParameter("cargo", nomePerfil);

		List<Perfil> res = q.getResultList();
		if (!res.isEmpty()) {
			return res.get(0);
		} else
			return null;
	}

	@Override
	public Perfil create(Perfil entity) {
		String p = entity.getCargo();
		try {
			em.persist(entity);
			Perfil newp = findByName(p);
			logger.info("Perfil " + p + " criado com sucesso na base de dados");
			return newp;
		} catch (Exception e) {
			logger.error("Erro ao criar o perfil " + p + " na base de dados");
			return null;
		}
	}

	@Override
	public Perfil update(Perfil entity) {
		try {
			Perfil p = em.merge(entity);
			logger.info("Perfil " + entity.getCargo()
					+ " atualizado com sucesso");
			return p;
		} catch (Exception e) {
			logger.error("Erro ao atualizar o perfil " + entity.getCargo()
					+ " na base de dados");
			return null;
		}
	}

	@Override
	public boolean delete(Perfil entity) {
		if (em.find(Perfil.class, entity.getId()) == null) {
			logger.error("Erro ao apagar o Perfil " + entity.getCargo()
					+ ". Cargo não encontrado");
			return false;
		} else {

			try {
				Perfil entityToBeRemoved = em.merge(entity);
				em.remove(entityToBeRemoved);
				em.flush();
				logger.info("Perfil " + entity.getCargo()
						+ " apagado com sucesso da base de dados");
				return true;
			} catch (Exception e) {
				logger.error("Erro ao apagar o perfil " + entity.getCargo());
				return false;
			}
		}
	}

	@Override
	public Perfil find(long pk) {
		TypedQuery<Perfil> q = em.createNamedQuery("Perfil.findById",
				Perfil.class).setParameter("id", pk);
		List<Perfil> res = q.getResultList();
		if (!res.isEmpty()) {
			return res.get(0);
		} else
			return null;
	}

	@Override
	public Collection<Perfil> findAll() {
		TypedQuery<Perfil> q = em.createNamedQuery("Perfil.findAll",
				Perfil.class);
		return q.getResultList();
	}

}
