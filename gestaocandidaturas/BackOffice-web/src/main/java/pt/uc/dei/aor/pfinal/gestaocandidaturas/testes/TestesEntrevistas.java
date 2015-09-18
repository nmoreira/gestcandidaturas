package pt.uc.dei.aor.pfinal.gestaocandidaturas.testes;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.CandidaturaService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.EntrevistaService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.EntrevistadorService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.GuiaoService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.PosicaoService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidatura;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Entrevista;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Entrevistador;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Guiao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Posicao;

@Named
@ViewScoped
public class TestesEntrevistas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private PosicaoService posicaoServ;

	@Inject
	private GuiaoService guiaoServ;

	@Inject
	private CandidaturaService candidaturaServ;

	@Inject
	private EntrevistadorService entrevistadorServ;

	@Inject
	private EntrevistaService entrevistaServ;

	private List<Posicao> todasPosicoes;
	private List<Guiao> todosGuioes;
	private List<Candidatura> todasCandidaturas;

	private Guiao guiaoSelecionado;
	private Posicao posicaoSelecionada;
	private Candidatura candidaturaSelecionada;

	@PostConstruct
	private void init() {
		todasPosicoes = posicaoServ.listaPosicoes();
		todosGuioes = guiaoServ.getTodosOsGuioes();
		todasCandidaturas = candidaturaServ.getCandidaturasSemEntrevistas();
	}

	public void atribuirGuiao() {
		posicaoSelecionada.setGuiao(guiaoSelecionado);
		posicaoServ.atualizaPosicao(posicaoSelecionada);
		List<Posicao> posicoes = posicaoServ.getPosicoesEmAberto();
		for (Posicao posicao : posicoes) {
			if (posicao.equals(posicaoSelecionada)) {
				posicaoSelecionada = posicao;
				System.out.println("encontrada");
				System.out.println("Nome do guião "
						+ posicaoSelecionada.getGuiao().getNome());
				break;
			}
		}
	}

	public void marcarEntrevista() {
		Entrevistador ent = entrevistadorServ.getEntrevistadorByLogin("ent");
		Entrevista entrevista = new Entrevista(new Date(), ent,
				candidaturaSelecionada);
		entrevistaServ.createNewEntrevista(entrevista);
	}

	public List<Posicao> getTodasPosicoes() {
		return todasPosicoes;
	}

	public void setTodasPosicoes(List<Posicao> todasPosicoes) {
		this.todasPosicoes = todasPosicoes;
	}

	public List<Guiao> getTodosGuioes() {
		return todosGuioes;
	}

	public void setTodosGuioes(List<Guiao> todosGuioes) {
		this.todosGuioes = todosGuioes;
	}

	public Guiao getGuiaoSelecionado() {
		return guiaoSelecionado;
	}

	public void setGuiaoSelecionado(Guiao guiaoSelecionado) {
		this.guiaoSelecionado = guiaoSelecionado;
		System.out.println("Guiao selecionado: "
				+ this.guiaoSelecionado.getNome());
	}

	public Posicao getPosicaoSelecionada() {
		return posicaoSelecionada;
	}

	public void setPosicaoSelecionada(Posicao posicaoSelecionada) {
		this.posicaoSelecionada = posicaoSelecionada;
		System.out.println("Posição selecionada: "
				+ this.posicaoSelecionada.getTitulo());
	}

	public Candidatura getCandidaturaSelecionada() {
		return candidaturaSelecionada;
	}

	public void setCandidaturaSelecionada(Candidatura candidaturaSelecionada) {
		this.candidaturaSelecionada = candidaturaSelecionada;
		System.out.println("Candidatura selecionada: "
				+ this.candidaturaSelecionada.getId());
	}

	public List<Candidatura> getTodasCandidaturas() {
		return todasCandidaturas;
	}

	public void setTodasCandidaturas(List<Candidatura> todasCandidaturas) {
		this.todasCandidaturas = todasCandidaturas;
	}

}
