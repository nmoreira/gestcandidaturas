package pt.uc.dei.aor.pfinal.gestaocandidaturas.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.CandidatoService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.UtilizadorService;

@Named
@ViewScoped
public class NovaCandidatura implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String cartamotivacao;
	private String fonte;
	
	private List<String> fontes = new ArrayList<>();
	
	public NovaCandidatura() {
		fontes.add("Expresso");
		fontes.add("Linkdin");
		fontes.add("Diário de Notícias");
		fontes.add("Jornal de Notícias");
		fontes.add("Centro de Emprego");
		fontes.add("Facebook");
		fontes.add("Amigos");
	}
	
	@Inject
	private CandidatoService candidatoServ;

	@Inject
	private UtilizadorService userServ;
	
	public List<String> sugerirfonte(String consulta){
		List<String> fontesugeridas = new ArrayList<>();
		for(String fonte : fontes){
			if(fonte.toLowerCase().startsWith(consulta.toLowerCase()))
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
	
	
	

}
