package pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.SimNao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.ISimNaoFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.SimNaoFacade;

/**
 * Session Bean implementation class SimNaoService
 */
@Stateless
@LocalBean
public class SimNaoService {

	@EJB(beanInterface = SimNaoFacade.class)
	private ISimNaoFacade simNaoFacade;

	/**
	 * Default constructor.
	 */
	public SimNaoService() {
		// TODO Auto-generated constructor stub
	}

	public boolean createNewQuestaoSimNao(SimNao newSimNao) {
		if (simNaoFacade.create(newSimNao) != null) {
			return true;
		} else {
			return false;
		}
	}

	public SimNao getQuestaoSimNaoById(long id) {
		return simNaoFacade.find(id);
	}

	public SimNao atualizarQuestaoSimNao(SimNao questaoSimNao) {
		return simNaoFacade.update(questaoSimNao);
	}

	public boolean apagaQuestaoSimNao(SimNao questaoSimNao) {
		return simNaoFacade.delete(questaoSimNao);
	}

}
