package pt.uc.dei.aor.pfinal.gestaocandidaturas.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
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
public class NovoGuiao implements Serializable {

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

	private String nomeGuiao;
	private String tipoQuestao;
	private String pergunta;

	private Guiao novoGuiao;

	@PostConstruct
	private void init() {
		novoGuiao = new Guiao();
		novoGuiao.setQuestoes(new ArrayList<Questao>());
	}

	public void gravarPerguntaTextoLivre() {
		TextoLivre p = new TextoLivre();
		p.setPergunta(pergunta);
		this.pergunta = "";
		this.tipoQuestao = "";
		p.setOrdem(novoGuiao.getQuestoes().size() + 1);
		novoGuiao.getQuestoes().add(p);
	}

	public void gravarPerguntaEscala1a5() {
		Escala1a5 p = new Escala1a5();
		p.setPergunta(pergunta);
		this.pergunta = "";
		this.tipoQuestao = "";
		p.setOrdem(novoGuiao.getQuestoes().size() + 1);
		novoGuiao.getQuestoes().add(p);
	}

	public void gravarPerguntaSimNao() {
		SimNao p = new SimNao();
		p.setPergunta(pergunta);
		this.pergunta = "";
		this.tipoQuestao = "";
		p.setOrdem(novoGuiao.getQuestoes().size() + 1);
		novoGuiao.getQuestoes().add(p);
	}

	public void gravaGuiao() {
		this.novoGuiao.setNome(nomeGuiao);
		for (Questao questao : novoGuiao.getQuestoes()) {
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
		if (guiaoServ.createNewGuiao(novoGuiao)) {
			DisplayMessages.addInfoMessage("Guião criado com sucesso");
		} else {
			DisplayMessages.addErrorMessage("Erro ao criar o guião");
		}
	}

	public void reordenar(ReorderEvent event) {
		reordenar();
	}

	private void reordenar() {
		List<Questao> lista = novoGuiao.getQuestoes();
		for (int i = 0; i < lista.size(); i++) {
			lista.get(i).setOrdem(i + 1);
		}
	}

	public void removeQuestao(Questao questao) {
		novoGuiao.getQuestoes().remove(questao);
		reordenar();
	}

	public TipoPergunta[] getTiposDeQuestoes() {
		return TipoPergunta.values();
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

	public String getNomeGuiao() {
		return nomeGuiao;
	}

	public void setNomeGuiao(String nomeGuiao) {
		this.nomeGuiao = nomeGuiao;
	}

	public Guiao getNovoGuiao() {
		return novoGuiao;
	}

	public void setNovoGuiao(Guiao novoGuiao) {
		this.novoGuiao = novoGuiao;
	}

}