package pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Escala1a5;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.Escala1a5Facade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.IEscala1a5Facade;

/**
 * Session Bean implementation class Escala1a5Service
 */
@Stateless
@LocalBean
public class Escala1a5Service {

	@EJB(beanInterface = Escala1a5Facade.class)
	private IEscala1a5Facade escala1a5Facade;

	/**
	 * Default constructor.
	 */
	public Escala1a5Service() {
		// TODO Auto-generated constructor stub
	}

	public boolean createNewQuestaoEscala1a5(Escala1a5 newEscala1a5) {
		if (escala1a5Facade.create(newEscala1a5) != null) {
			return true;
		} else {
			return false;
		}
	}

	public Escala1a5 getQuestaEscala1a5ById(long id) {
		return escala1a5Facade.find(id);
	}

	public Escala1a5 atualizarQuestaoEscala1a5(Escala1a5 questaoEscala1a5) {
		return escala1a5Facade.update(questaoEscala1a5);
	}

	public boolean apagaQuestaoEscala1a5(Escala1a5 questaoEscala1a5) {
		return escala1a5Facade.delete(questaoEscala1a5);
	}

}
