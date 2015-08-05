package pt.uc.dei.aor.pfinal.gestaocandidaturas.facades;

import java.util.Collection;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidato;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Perfil;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.Encript;

/**
 * Session Bean implementation class CandidatoFacade
 */
@Stateless
@LocalBean
public class CandidatoFacade implements ICandidatoFacade {

	@PersistenceContext(unitName = "gestcandidaturas")
	private EntityManager em;

	@Inject
	private PerfilFacade pf;

	/**
	 * Default constructor.
	 */
	public CandidatoFacade() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Candidato create(Candidato entity) {
		entity.setPassword(Encript.encript(entity.getPassword()));
		em.persist(entity);
		return entity;
	}

	@Override
	public Candidato update(Candidato entity) {
		return em.merge(entity);
	}

	@Override
	public void delete(Candidato entity) {
		Candidato entityToBeRemoved = em.merge(entity);
		em.remove(entityToBeRemoved);
	}

	@Override
	public Candidato find(long pk) {
		TypedQuery<Candidato> q = em.createNamedQuery("Candidato.findById",
				Candidato.class).setParameter("id", pk);
		if (!q.getResultList().isEmpty())
			return q.getResultList().get(0);
		else
			return null;
	}

	@Override
	public Collection<Candidato> findAll() {
		TypedQuery<Candidato> q = em.createNamedQuery("Candidato.findAll",
				Candidato.class);
		return q.getResultList();
	}

	@Override
	public Candidato findByLogin(String login) {
		TypedQuery<Candidato> q = em.createNamedQuery("Candidato.findByLogin",
				Candidato.class).setParameter("login", login);
		if (!q.getResultList().isEmpty())
			return q.getResultList().get(0);
		else
			return null;
	}

	@Override
	public Candidato findByEmail(String email) {
		TypedQuery<Candidato> q = em.createNamedQuery("Candidato.findByLogin",
				Candidato.class).setParameter("email", email);
		if (!q.getResultList().isEmpty())
			return q.getResultList().get(0);
		else
			return null;
	}

	@Override
	public Perfil getPerfilCandidato() {
		Perfil candidato = pf.findByName("CANDIDATO");
		if (candidato == null) {
			pf.create(new Perfil("CANDIDATO"));
			candidato = pf.findByName("CANDIDATO");
			return candidato;
		} else {
			return candidato;
		}
	}

	@Override
	public void changePassword(long userId, String newPassword) {
		Candidato user = find(userId);
		user.setPassword(Encript.encript(newPassword));
		update(user);
	}

}
