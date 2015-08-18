package pt.uc.dei.aor.pfinal.gestaocandidaturas.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.CandidatoService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.UtilizadorService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidato;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Posicao;

@Named
@ViewScoped
public class NovaCandidatura implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cartamotivacao;
	private String fonte;
	private Candidato candidato;
	private Posicao posicao;

	private List<String> fontes = new ArrayList<>();

	@Inject
	private CandidatoService candidatoServ;

	@Inject
	private UtilizadorService userServ;

	@Inject
	private CurrentSession current;

	public NovaCandidatura() {
	}

	@PostConstruct
	private void init() {
		carregaCandidaturas();
		this.candidato = (Candidato) current.getCurrentUser();
	}

	private void carregaCandidaturas() {
		fontes.add("Expresso");
		fontes.add("Linkdin");
		fontes.add("Diário de Notícias");
		fontes.add("Jornal de Notícias");
		fontes.add("Centro de Emprego");
		fontes.add("Facebook");
		fontes.add("Amigos");
	}

	public List<String> sugerirfonte(String consulta) {
		List<String> fontesugeridas = new ArrayList<>();
		for (String fonte : fontes) {
			if (fonte.toLowerCase().startsWith(consulta.toLowerCase()))
				fontesugeridas.add(fonte);
		}
		return fontesugeridas;
	}

	public String getCartamotivacao() {
		return cartamotivacao;
	}

	public void setCartamotivacao(String cartamotivacao) {
		this.cartamotivacao = cartamotivacao;
	}

	public String getFonte() {
		return fonte;
	}

	public void setFonte(String fonte) {
		this.fonte = fonte;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public Posicao getPosicao() {
		return posicao;
	}

	public void setPosicao(Posicao posicao) {
		this.posicao = posicao;
	}

}
