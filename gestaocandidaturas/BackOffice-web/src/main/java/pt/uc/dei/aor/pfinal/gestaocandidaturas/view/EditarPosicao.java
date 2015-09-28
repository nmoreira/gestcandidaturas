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

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.CandidaturaService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.GuiaoService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.PosicaoService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.ResponsavelPosicaoService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Guiao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Posicao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.ResponsavelPosicao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.mail.CommonsMail;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.AreaTecnica;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.DisplayMessages;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.EstadoPosicao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.LocalPosicao;

@Named
@ViewScoped
public class EditarPosicao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private PosicaoService posicaoServ;

	@Inject
	private CandidaturaService candidaturaServ;

	@Inject
	private ResponsavelPosicaoService respServ;

	@Inject
	private GuiaoService guiaoServ;

	@Inject
	private CommonsMail mail;

	private Posicao posicao;
	private String codPosicao;

	private final List<String> CANAIS_COMUNICACAO = new ArrayList<>();
	private final List<Guiao> GUIOES_DISPONIVEIS = new ArrayList<>();
	private final List<ResponsavelPosicao> RESPONSAVEIS = new ArrayList<>();

	@PostConstruct
	private void init() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		long posicaoid = Long.parseLong(params.get("posicaoid"));
		this.posicao = posicaoServ.getPosicaoById(posicaoid);
		this.posicao.setCandidaturas(candidaturaServ
				.getCandidaturasByPosicaoId(posicaoid));

		this.codPosicao = posicao.getCodPosicao();

		loadResponsaveis();
		loadCanaisComunicacao();
		loadGuioesDisponiveis();
	}

	public void validaCodPosicao() {
		if (!codPosicao.equals(posicao.getCodPosicao())) {
			if (posicaoServ.existeCodPosicao(codPosicao)) {
				DisplayMessages
						.addErrorMessage("O Cód. de posição introduzido já existe! Por favor escolha outro.");
			} else {
				posicao.setCodPosicao(codPosicao);
			}
		}
	}

	private void loadResponsaveis() {
		List<ResponsavelPosicao> list = respServ.getAllResponsaveisPosicao();
		RESPONSAVEIS.clear();
		for (ResponsavelPosicao responsavelPosicao : list) {
			RESPONSAVEIS.add(responsavelPosicao);
		}
	}

	private void loadGuioesDisponiveis() {
		GUIOES_DISPONIVEIS.addAll(guiaoServ.getGuioesDisponiveis());
	}

	private void loadCanaisComunicacao() {
		CANAIS_COMUNICACAO.add("Critical Software website");
		CANAIS_COMUNICACAO.add("Linkedin");
		CANAIS_COMUNICACAO.add("Glassdoor");
		CANAIS_COMUNICACAO.add("Facebook");
	}

	public void atualizarPosicao() {
		if (!codPosicao.equals(posicao.getCodPosicao())) {
			if (posicaoServ.existeCodPosicao(codPosicao)) {
				DisplayMessages
						.addErrorMessage("O Cód. de posição introduzido já existe! Por favor escolha outro.");
			} else {
				posicao.setCodPosicao(codPosicao);
			}
		} else {
			posicaoServ.atualizaPosicao(posicao);
			DisplayMessages.addInfoMessage("Posição atualizada com sucesso!");
			mail.enviaEmailSimples(
					"Atualização de Posição",
					"A posição "
							+ posicao.getTitulo()
							+ " na qual você é o gesto, foi atualizada.\n"
							+ "Por favor consulte a posição na aplicação para conhecer as alterações.",
					posicao.getResponsavel());
		}

	}

	public List<String> sugerirCanaisComunicacao(String query) {
		List<String> sugestao = new ArrayList<>();
		for (String canal : this.CANAIS_COMUNICACAO) {
			if (canal.toLowerCase().contains(query.toLowerCase())) {
				sugestao.add(canal);
			}
		}
		return sugestao;
	}

	public EstadoPosicao[] getEstadoPosicao() {
		return EstadoPosicao.values();
	}

	public AreaTecnica[] getAreas() {
		return AreaTecnica.values();
	}

	public LocalPosicao[] getLocais() {
		return LocalPosicao.values();
	}

	public Posicao getPosicao() {
		return posicao;
	}

	public void setPosicao(Posicao posicao) {
		this.posicao = posicao;
	}

	public List<String> getCANAIS_COMUNICACAO() {
		return CANAIS_COMUNICACAO;
	}

	public List<Guiao> getGUIOES_DISPONIVEIS() {
		return GUIOES_DISPONIVEIS;
	}

	public List<ResponsavelPosicao> getRESPONSAVEIS() {
		return RESPONSAVEIS;
	}

	public String getCodPosicao() {
		return codPosicao;
	}

	public void setCodPosicao(String codPosicao) {
		this.codPosicao = codPosicao;
	}

}
