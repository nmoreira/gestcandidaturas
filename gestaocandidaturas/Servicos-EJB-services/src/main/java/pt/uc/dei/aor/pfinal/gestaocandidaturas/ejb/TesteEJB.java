package pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Utilizador;

/**
 * Session Bean implementation class TesteEJB
 */
@Stateless
@LocalBean
public class TesteEJB {

	@PersistenceContext(name = "gestcandidaturas")
	EntityManager em;
	Query q;

	/**
	 * Default constructor.
	 */
	public TesteEJB() {
		// TODO Auto-generated constructor stub
	}

	public void saveUser(Utilizador user) {
		em.persist(user);
	}

	public Utilizador findUserByEmail(String email) {
		q = em.createQuery("from Utilizador u where u.email = :email");
		q.setParameter("email", email);

		Utilizador user = null;

		try {
			user = (Utilizador) q.getSingleResult();
			return user;
		} catch (NoResultException e) {
			return null;
		}

	}

	public List<Utilizador> getUtilizadores() {
		q = em.createQuery("from Utilizador u");
		List<Utilizador> resultado = q.getResultList();
		return resultado;
	}

}
