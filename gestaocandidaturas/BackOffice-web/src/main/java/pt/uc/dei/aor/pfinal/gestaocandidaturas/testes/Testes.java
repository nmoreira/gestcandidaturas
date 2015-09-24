package pt.uc.dei.aor.pfinal.gestaocandidaturas.testes;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.CandidatoService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.CandidaturaService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.EntrevistaService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.EntrevistadorService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.PosicaoService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidato;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidatura;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Entrevista;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Posicao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.AreaTecnica;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.EstadoPosicao;

@Named
@ViewScoped
public class Testes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CandidatoService candidatoServ;

	@Inject
	private PosicaoService pos;

	@Inject
	private CandidaturaService candidaturaServ;

	@Inject
	private EntrevistadorService entrevistadorServ;

	@Inject
	private EntrevistaService entrevistaServ;

	private List<Candidato> resultado;

	private List<Candidatura> candEspontaneas;

	private Candidatura selecionada;

	public void pesquisa() {
		Candidato c = new Candidato("cc", "123", "sssnomesss", "apelido",
				"email1", "morada1", "cidade1", 1325645, 112123354, "pais",
				"curso", "escola", "cv", "idLinkedin");
		Candidato c2 = new Candidato("csdfgc", "123", "ssssdfgsss", "apelido",
				"email2", "morada2", "cidade2", 13256455, 912123354, "pais",
				"curso", "escola", "cv", "idLinkedin");

		Posicao p = new Posicao(new Date(), "pos1", "titulo",
				EstadoPosicao.ABERTA, 3, null, 0, null, "",
				AreaTecnica.Dot_Net_Development, "", "", null);

		candidatoServ.createNewCandidato(c);
		candidatoServ.createNewCandidato(c2);

		Posicao p2 = pos.createNewPosicao(p);

		Candidatura cand = new Candidatura("", "", p2,
				candidatoServ.getCandidatoByLogin("cc"));

		candidaturaServ.createNewCandidatura(cand);

		Candidatura cand2 = new Candidatura("", "", p2,
				candidatoServ.getCandidatoByLogin("csdfgc"));

		candidaturaServ.createNewCandidatura(cand2);

		List<Candidato> a = candidatoServ.pesquisaCandidatoByNome("ss");
		System.out.println(a.size());
		for (Candidato candidato : a) {
			System.out.println("nº candidaturas "
					+ candidato.getCandidaturas().size());
		}

		List<Candidatura> pesquisa = candidaturaServ
				.pesquisaCandidaturasByCandidatoNome("ss");
		System.out.println("resultado da pesquisa de candidaturas: "
				+ pesquisa.size());
		for (Candidatura candidatura : pesquisa) {
			System.out.println(candidatura.getPosicao().getTitulo());
		}
	}

	public void candidaturasSemEntrevistas() {
		List<Candidatura> cand = candidaturaServ
				.getCandidaturasSemEntrevistas();
		System.out.println("Candidaturas sem entrevistas: " + cand.size());
		if (cand.size() > 0) {
			Candidatura c1 = cand.get(0);
			Entrevista e1 = new Entrevista(null,
					entrevistadorServ.getEntrevistadorByLogin("ent"), c1);
			entrevistaServ.createNewEntrevista(e1);
		}
		cand = candidaturaServ.getCandidaturasSemEntrevistas();
		System.out.println("Candidaturas sem entrevistas: " + cand.size());
	}

	public void candidaturasEspontaneas() {
		candEspontaneas = candidaturaServ.getCandidaturasEspontaneas();
	}

	public List<Candidato> getResultado() {
		return resultado;
	}

	public void setResultado(List<Candidato> resultado) {
		this.resultado = resultado;
	}

	public List<Candidatura> getCandEspontaneas() {
		return candEspontaneas;
	}

	public void setCandEspontaneas(List<Candidatura> candEspontaneas) {
		this.candEspontaneas = candEspontaneas;
	}

	public Candidatura getSelecionada() {
		return selecionada;
	}

	public void setSelecionada(Candidatura selecionada) {
		this.selecionada = selecionada;
		System.out.println("selecionada: " + this.selecionada);
	}

}
