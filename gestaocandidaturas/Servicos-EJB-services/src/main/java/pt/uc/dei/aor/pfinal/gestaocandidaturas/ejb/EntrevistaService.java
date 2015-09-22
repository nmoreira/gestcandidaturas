package pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Entrevista;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Guiao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Questao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.EntrevistaFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.GuiaoFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.IEntrevistaFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.IGuiaoFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.IQuestaoFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.QuestaoFacade;

/**
 * Session Bean implementation class EntrevistaService
 */
@Stateless
@LocalBean
public class EntrevistaService {

	@EJB(beanInterface = EntrevistaFacade.class)
	private IEntrevistaFacade entrevistaFacade;

	@EJB(beanInterface = GuiaoFacade.class)
	private IGuiaoFacade guiaoFacade;

	@EJB(beanInterface = QuestaoFacade.class)
	private IQuestaoFacade questaoFacade;

	/**
	 * Default constructor.
	 */
	public EntrevistaService() {
		// TODO Auto-generated constructor stub
	}

	public boolean createNewEntrevista(Entrevista newEntrevista) {
		Guiao guiao = newEntrevista.getCandidatura().getPosicao().getGuiao();
		System.out.println("guiao: " + guiao);

		List<Questao> original = guiaoFacade
				.getQuestoesByGuiaoId(guiao.getId());

		List<Questao> copia = new ArrayList<Questao>();
		for (Questao questao : original) {
			Questao copiada = questao.copiaPergunta(questao);
			questaoFacade.create(copiada);
			copia.add(copiada);
		}
		newEntrevista.setQuestoesEntrevista(copia);
		if (entrevistaFacade.create(newEntrevista) != null) {
			return true;
		} else {
			return false;
		}
	}

	public Entrevista getEntrevistaById(long id) {
		return entrevistaFacade.find(id);
	}

	public boolean atualizarEntrevista(Entrevista ent) {
		if (entrevistaFacade.update(ent) != null) {
			return true;
		} else {
			return false;
		}
	}

	public List<Entrevista> getEntrevistaByCandidatura(long candidaturaId) {
		return entrevistaFacade.findByCandidatura(candidaturaId);
	}

	public List<Entrevista> getEntrevistaByEntrevistador(long entrevistadorId) {
		return entrevistaFacade.findByEntrevistador(entrevistadorId);
	}

	public List<Entrevista> getEntrevistaByData(Date data) {
		return entrevistaFacade.findByData(data);
	}

}
