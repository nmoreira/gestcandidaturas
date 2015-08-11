package pt.uc.dei.aor.pfinal.gestaocandidaturas.facades;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.ResponsavelPosicao;

/**
 * Session Bean implementation class AdministradorFacade
 */
@Stateless()
@LocalBean
public class ResponsavelPosicaoFacade implements IResponsavelPosicaoFacade {

	@PersistenceContext(unitName = "gestcandidaturas")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public ResponsavelPosicaoFacade() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<ResponsavelPosicao> findAll() {
		TypedQuery<ResponsavelPosicao> q = em.createNamedQuery(
				"ResponsavelPosicao.findAll", ResponsavelPosicao.class);
		return q.getResultList();
	}

	@Override
	public ResponsavelPosicao findById(long id) {
		TypedQuery<ResponsavelPosicao> q = em.createNamedQuery(
				"ResponsavelPosicao.findById", ResponsavelPosicao.class)
				.setParameter("id", id);
		if (!q.getResultList().isEmpty())
			return q.getResultList().get(0);
		else
			return null;
	}

}
