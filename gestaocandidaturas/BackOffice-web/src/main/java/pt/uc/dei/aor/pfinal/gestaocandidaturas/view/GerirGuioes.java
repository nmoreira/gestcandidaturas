package pt.uc.dei.aor.pfinal.gestaocandidaturas.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.Escala1a5Service;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.GuiaoService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.SimNaoService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.TextoLivreService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Escala1a5;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Guiao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Questao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.SimNao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.TextoLivre;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.DisplayMessages;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.TipoPergunta;

@Named
@ViewScoped
public class GerirGuioes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private GuiaoService guiaoServ;

	@Inject
	private TextoLivreService textolivreServ;

	@Inject
	private Escala1a5Service escala1a5Serv;

	@Inject
	private SimNaoService simNaoServ;

	private List<Guiao> guioes;
	private Guiao guiaoSelecionado;

	@PostConstruct
	private void init() {
		guioes = guiaoServ.getGuioesDisponiveisFetchQuestoes();
	}

	public void removerGuiao() {
		if (guiaoServ.guiaoEmUso(guiaoSelecionado.getId())) {
			guiaoSelecionado.setDisponivel(false);
			if (guiaoServ.atualizarGuiao(guiaoSelecionado) != null) {
				DisplayMessages
						.addWarnMessage("O guião selecionado está a ser usado em alguma posição."
								+ "Deixará de estar disponível para novas posições, mas continua em uso nas posições a que foi atribuido.");
				DisplayMessages
						.addInfoMessage("Guião removido com sucesso (colocado como indisponível)");
			} else {
				DisplayMessages.addErrorMessage("Erro ao modificar o guiao");
			}
		} else {
			if (guiaoServ.apagaGuiao(guiaoSelecionado)) {
				for (Questao questao : guiaoSelecionado.getQuestoes()) {
					if (questao.getTipo().equals(TipoPergunta.ESCALA_1_5)) {
						escala1a5Serv
								.apagaQuestaoEscala1a5((Escala1a5) questao);
					} else if (questao.getTipo().equals(TipoPergunta.LIVRE)) {
						textolivreServ
								.apagaQuestaoTextoLivre((TextoLivre) questao);
					} else if (questao.getTipo().equals(TipoPergunta.SIM_NAO)) {
						simNaoServ.apagaQuestaoSimNao((SimNao) questao);
					}
				}
				DisplayMessages.addInfoMessage("Guião removido com sucesso!");
			} else {
				DisplayMessages.addErrorMessage("Erro ao remover o guião");
			}
		}
		init();
	}

	public List<Guiao> getGuioes() {
		return guioes;
	}

	public void setGuioes(List<Guiao> guioes) {
		this.guioes = guioes;
	}

	public Guiao getGuiaoSelecionado() {
		return guiaoSelecionado;
	}

	public void selecionarGuiao(Guiao guiaoSelecionado) {
		this.guiaoSelecionado = guiaoSelecionado;
	}

}
