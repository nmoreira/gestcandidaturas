package pt.uc.dei.aor.pfinal.gestaocandidaturas.facades;

import java.util.Collection;
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

	static Logger logger = LoggerFactory.getLogger(CandidatoFacade.class);

	@Inject
	private PerfilFacade pf;

	private Query q;

	/**
	 * Default constructor.
	 */
	public CandidatoFacade() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Candidato create(Candidato entity) {
		entity.setPassword(Encript.encript(entity.getPassword()));
		String login = entity.getLogin();
		try {
			em.persist(entity);
			Candidato newUser = findByLogin(login);
			logger.info("Candidato " + login
					+ " criado com sucesso na base de dados");
			return newUser;
		} catch (Exception e) {
			logger.error("Erro ao criar o candidato " + login
					+ " na base de dados");
			return null;
		}
	}

	@Override
	public Candidato update(Candidato entity) {
		try {
			Candidato updatedUser = em.merge(entity);
			logger.info("Candidato " + entity.getLogin()
					+ " atualizado com sucesso");
			return updatedUser;
		} catch (Exception e) {
			logger.error("Erro ao atualizar o candidato " + entity.getLogin()
					+ " na base de dados");
			return null;
		}
	}

	@Override
	public boolean delete(Candidato entity) {
		if (em.find(Candidato.class, entity.getId()) == null) {
			logger.error("Erro ao apagar o candidato " + entity.getLogin()
					+ ". Candidato não encontrado");
			return false;
		} else {
			try {
				Candidato entityToBeRemoved = em.merge(entity);
				em.remove(entityToBeRemoved);
				em.flush();
				logger.info("Candidato " + entity.getLogin()
						+ " apagado com sucesso da base de dados");
				return true;
			} catch (Exception e) {
				logger.error("Erro ao apagar o candidato " + entity.getLogin());
				return false;
			}
		}
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
		logger.info("Password do candidato " + user.getLogin()
				+ " atualizada com sucesso");
	}

	@Override
	public Candidato createBypassingPassword(Candidato candidato) {
		String login = candidato.getLogin();
		try {
			em.persist(candidato);
			Candidato newUser = findByLogin(login);
			logger.info("Candidato " + login
					+ " criado com sucesso na base de dados");
			return newUser;
		} catch (Exception e) {
			logger.error("Erro ao criar o candidato " + login
					+ " na base de dados");
			return null;
		}
	}

	@Override
	public List<String> getCartasFromCandidatoId(long id) {
		q = em.createQuery("select c from Candidato c left join fetch c.cartas where c.id = :id");
		q.setParameter("id", id);
		Candidato c = (Candidato) q.getSingleResult();
		System.out.println(c.getNome());
		return c.getCartas();
	}
}
