package pt.uc.dei.aor.pfinal.gestaocandidaturas.facades;

import java.util.ArrayList;
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

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Posicao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.EstadoPosicao;

/**
 * Session Bean implementation class AdministradorFacade
 */
@Stateless()
@LocalBean
public class PosicaoFacade implements IPosicaoFacade {

	@PersistenceContext(unitName = "gestcandidaturas")
	private EntityManager em;

	static Logger logger = LoggerFactory.getLogger(PosicaoFacade.class);

	private Query q;

	/**
	 * Default constructor.
	 */
	public PosicaoFacade() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Posicao create(Posicao entity) {
		String codPosicao = entity.getCodPosicao();
		try {
			em.persist(entity);
			Posicao pos = findByCodPosicao(codPosicao);
			logger.info("Posicao " + codPosicao
					+ " criada com sucesso na base de dados");
			return pos;
		} catch (Exception e) {
			logger.error("Erro ao criar a posição " + codPosicao
					+ " na base de dados");
			return null;
		}
	}

	@Override
	public Posicao update(Posicao entity) {
		try {
			Posicao updatedPos = em.merge(entity);
			logger.info("Posicao " + entity.getCodPosicao()
					+ " atualizada com sucesso");
			return updatedPos;
		} catch (Exception e) {
			logger.error("Erro ao atualizar a Posicao "
					+ entity.getCodPosicao() + " na base de dados");
			return null;
		}
	}

	@Override
	public boolean delete(Posicao entity) {
		if (em.find(Posicao.class, entity.getId()) == null) {
			logger.error("Erro ao apagar a Posição " + entity.getCodPosicao()
					+ ". Posição não encontrada");
			return false;
		} else {

			try {
				Posicao entityToBeRemoved = em.merge(entity);
				em.remove(entityToBeRemoved);
				em.flush();
				logger.info("Posição " + entity.getCodPosicao()
						+ " apagada com sucesso da base de dados");
				return true;
			} catch (Exception e) {
				logger.error("Erro ao apagar a Posição "
						+ entity.getCodPosicao());
				return false;
			}
		}
	}

	@Override
	public Posicao find(long pk) {
		TypedQuery<Posicao> q = em.createNamedQuery("Posicao.findById",
				Posicao.class).setParameter("id", pk);
		if (!q.getResultList().isEmpty())
			return q.getResultList().get(0);
		else
			return null;
	}

	@Override
	public Collection<Posicao> findAll() {
		TypedQuery<Posicao> q = em.createNamedQuery("Posicao.findAll",
				Posicao.class);
		return q.getResultList();
	}

	@Override
	public List<Posicao> findByTitulo(String titulo) {
		TypedQuery<Posicao> q = em.createNamedQuery("Posicao.findByTitulo",
				Posicao.class).setParameter("titulo", titulo);
		return q.getResultList();
	}

	@Override
	public List<Posicao> findByEmpresa(String empresa) {
		TypedQuery<Posicao> q = em.createNamedQuery("Posicao.findByEmpresa",
				Posicao.class).setParameter("empresa", empresa);
		return q.getResultList();
	}

	@Override
	public Posicao findByCodPosicao(String codPosicao) {
		TypedQuery<Posicao> q = em.createNamedQuery("Posicao.findByCodPosicao",
				Posicao.class).setParameter("codPosicao", codPosicao);
		if (!q.getResultList().isEmpty())
			return q.getResultList().get(0);
		else
			return null;
	}

	@Override
	public List<Posicao> getPosicoesEmAberto() {
		List<Posicao> result = new ArrayList<>();
		q = em.createQuery("from Posicao p where p.estado = :estado");
		q.setParameter("estado", EstadoPosicao.ABERTA);
		result = q.getResultList();
		return result;
	}

}
