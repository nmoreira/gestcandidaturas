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

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.CandidatoService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.CandidaturaService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.PosicaoService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.UtilizadorService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidato;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidatura;
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
	private PosicaoService posicaoServ;

	@Inject
	private CurrentSession current;

	@Inject
	private CandidaturaService candidaturaServ;

	public NovaCandidatura() {
	}

	@PostConstruct
	private void init() {
		carregaFontes();
		this.candidato = (Candidato) current.getCurrentUser();

		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		long posid = Long.parseLong(params.get("posid"));
		this.posicao = posicaoServ.getPosicaoById(posid);
	}

	private void carregaFontes() {
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

	public void Candidatar() {
		Candidatura candidatura = new Candidatura(cartamotivacao, fonte,
				posicao, candidato);
		candidaturaServ.createNewCandidatura(candidatura);
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
