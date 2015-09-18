package pt.uc.dei.aor.pfinal.gestaocandidaturas.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.ReorderEvent;

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
public class EditarGuiao implements Serializable {

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

	private Guiao guiao;
	private String tipoQuestao;
	private String pergunta;
	private List<Questao> apagarQuestoes;// questões a remover da base de dados

	@PostConstruct
	private void init() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		long guiaoid = Long.parseLong(params.get("guiaoid"));

		this.setGuiao(guiaoServ.getGuiaoByIdFetchQuestoes(guiaoid));
		apagarQuestoes = new ArrayList<>();
	}

	public void gravarPerguntaTextoLivre() {
		TextoLivre p = new TextoLivre();
		p.setPergunta(pergunta);
		this.pergunta = "";
		this.tipoQuestao = "";
		p.setOrdem(guiao.getQuestoes().size() + 1);
		guiao.getQuestoes().add(p);
	}

	public void gravarPerguntaEscala1a5() {
		Escala1a5 p = new Escala1a5();
		p.setPergunta(pergunta);
		this.pergunta = "";
		this.tipoQuestao = "";
		p.setOrdem(guiao.getQuestoes().size() + 1);
		guiao.getQuestoes().add(p);
	}

	public void gravarPerguntaSimNao() {
		SimNao p = new SimNao();
		p.setPergunta(pergunta);
		this.pergunta = "";
		this.tipoQuestao = "";
		p.setOrdem(guiao.getQuestoes().size() + 1);
		guiao.getQuestoes().add(p);
	}

	public void reordenar(ReorderEvent event) {
		reordenar();
	}

	private void reordenar() {
		List<Questao> lista = guiao.getQuestoes();
		for (int i = 0; i < lista.size(); i++) {
			lista.get(i).setOrdem(i + 1);
		}
	}

	public void removeQuestao(Questao questao) {
		guiao.getQuestoes().remove(questao);
		reordenar();
		if (questao.getId() != 0)
			apagarQuestoes.add(questao);
	}

	public void atualizarGuiao() {
		for (Questao questao : guiao.getQuestoes()) {
			if (questao.getId() == 0) {
				if (questao.getTipo().equals(TipoPergunta.LIVRE)) {
					if (!textolivreServ
							.createNewQuestaoTextoLivre((TextoLivre) questao))
						DisplayMessages
								.addErrorMessage("Erro ao gravar a questão nº"
										+ questao.getOrdem());
				} else if (questao.getTipo().equals(TipoPergunta.ESCALA_1_5)) {
					if (!escala1a5Serv
							.createNewQuestaoEscala1a5((Escala1a5) questao))
						DisplayMessages
								.addErrorMessage("Erro ao gravar a questão nº"
										+ questao.getOrdem());
				} else if (questao.getTipo().equals(TipoPergunta.SIM_NAO)) {
					if (!simNaoServ.createNewQuestaoSimNao((SimNao) questao))
						DisplayMessages
								.addErrorMessage("Erro ao gravar a questão nº"
										+ questao.getOrdem());
				}
			}
		}
		if (guiaoServ.atualizarGuiao(guiao) != null) {
			DisplayMessages.addInfoMessage("Guião atualizado com sucesso");
		} else {
			DisplayMessages.addErrorMessage("Erro ao atualizar o guião");
		}
		apagaQuestoes();
	}

	public TipoPergunta[] getTiposDeQuestoes() {
		return TipoPergunta.values();
	}

	private void apagaQuestoes() {
		for (Questao questao : apagarQuestoes) {
			if (questao.getTipo().equals(TipoPergunta.ESCALA_1_5)) {
				escala1a5Serv.apagaQuestaoEscala1a5((Escala1a5) questao);
			} else if (questao.getTipo().equals(TipoPergunta.LIVRE)) {
				textolivreServ.apagaQuestaoTextoLivre((TextoLivre) questao);
			} else if (questao.getTipo().equals(TipoPergunta.SIM_NAO)) {
				simNaoServ.apagaQuestaoSimNao((SimNao) questao);
			}
		}
	}

	public Guiao getGuiao() {
		return guiao;
	}

	public void setGuiao(Guiao guiao) {
		this.guiao = guiao;
	}

	public String getTipoQuestao() {
		return tipoQuestao;
	}

	public void setTipoQuestao(String tipoQuestao) {
		this.tipoQuestao = tipoQuestao;
	}

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

}
