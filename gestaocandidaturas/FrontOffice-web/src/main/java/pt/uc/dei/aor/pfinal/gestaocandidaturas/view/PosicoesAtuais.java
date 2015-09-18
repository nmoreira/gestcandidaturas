package pt.uc.dei.aor.pfinal.gestaocandidaturas.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.PosicaoService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Posicao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.DisplayMessages;

@Named
@ViewScoped
public class PosicoesAtuais implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Posicao> listaPosicoes;

	@Inject
	private PosicaoService posicaoService;

	@Inject
	private CurrentSession current;

	@PostConstruct
	private void init() {
		loadPosicoesAbertas();
	}

	public String candidatar() {
		if (current.getCurrentUser() == null) {
			DisplayMessages
					.addWarnMessage("Inicie sessão antes de se candidatar a uma posição");
			return "";
		} else {
			return "/paginas/candidato/candidaturas";
		}
	}

	private void loadPosicoesAbertas() {
		listaPosicoes = posicaoService.getPosicoesEmAberto();
	}

	public List<Posicao> getListaPosicoes() {
		return listaPosicoes;
	}

	public void setListaPosicoes(List<Posicao> listaPosicoes) {
		this.listaPosicoes = listaPosicoes;
	}

}
