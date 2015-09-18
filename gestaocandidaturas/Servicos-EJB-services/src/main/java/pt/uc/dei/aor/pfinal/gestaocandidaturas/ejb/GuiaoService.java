package pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Guiao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Questao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.GuiaoFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.IGuiaoFacade;

/**
 * Session Bean implementation class GuiaoService
 */
@Stateless
@LocalBean
public class GuiaoService {

	@EJB(beanInterface = GuiaoFacade.class)
	private IGuiaoFacade guiaoFacade;

	/**
	 * Default constructor.
	 */
	public GuiaoService() {
		// TODO Auto-generated constructor stub
	}

	public boolean createNewGuiao(Guiao newGuiao) {
		if (guiaoFacade.create(newGuiao) != null)
			return true;
		else
			return false;
	}

	public Guiao getGuiaoById(long id) {
		return guiaoFacade.find(id);
	}

	public Guiao atualizarGuiao(Guiao guiao) {
		return guiaoFacade.update(guiao);
	}

	public boolean apagaGuiao(Guiao guiao) {
		return guiaoFacade.delete(guiao);
	}

	public List<Guiao> getTodosOsGuioes() {
		return (List<Guiao>) guiaoFacade.findAll();
	}

	public List<Questao> getQuestoesByGuiaoId(long id) {
		return guiaoFacade.getQuestoesByGuiaoId(id);
	}

	public List<Guiao> getGuioesDisponiveis() {
		return guiaoFacade.getGuioesDisponiveis();
	}

	public List<Guiao> getGuioesDisponiveisFetchQuestoes() {
		return guiaoFacade.getGuioesDisponiveisFetchQuestoes();
	}

	public boolean guiaoEmUso(long guiaoId) {
		return guiaoFacade.guiaoEmUso(guiaoId);
	}

	public Guiao getGuiaoByIdFetchQuestoes(long guiaoId) {
		return guiaoFacade.getGuiaoByIdFetchQuestoes(guiaoId);
	}

}
