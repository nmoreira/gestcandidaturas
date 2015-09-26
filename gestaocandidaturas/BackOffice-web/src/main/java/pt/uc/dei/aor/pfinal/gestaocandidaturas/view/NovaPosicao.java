package pt.uc.dei.aor.pfinal.gestaocandidaturas.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
public class NovaPosicao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date dataAbertura;
	private String codPosicao;
	private String titulo;
	private List<LocalPosicao> local;
	private EstadoPosicao estado;
	private int quantidadeVagas;
	private Date dataFecho;
	private int sla;
	private ResponsavelPosicao responsavel;
	private String empresa;
	private AreaTecnica areaTecnica;
	private String descricao;
	private String canaisPublicacao;
	private Guiao guiao;

	public static final List<ResponsavelPosicao> RESPONSAVEIS = new ArrayList<>();
	private final List<String> CANAIS_COMUNICACAO = new ArrayList<>();
	private final List<Guiao> GUIOES_DISPONIVEIS = new ArrayList<>();

	@Inject
	private PosicaoService posicaoServ;

	@Inject
	private ResponsavelPosicaoService respServ;

	@Inject
	private GuiaoService guiaoServ;

	@Inject
	private CommonsMail mail;

	/**
	 * Default constructor.
	 */
	public NovaPosicao() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	private void load() {
		loadResponsaveis();
		loadCanaisComunicacao();
		loadGuioesDisponiveis();
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

	public EstadoPosicao[] getEstadoPosicao() {
		return EstadoPosicao.values();
	}

	public AreaTecnica[] getAreas() {
		return AreaTecnica.values();
	}

	public LocalPosicao[] getLocais() {
		return LocalPosicao.values();
	}

	public List<String> getCANAIS_COMUNICACAO() {
		return CANAIS_COMUNICACAO;
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

	public void validaCodPosicao() {
		if (posicaoServ.existeCodPosicao(codPosicao)) {
			DisplayMessages
					.addErrorMessage("O Cód. de posição introduzido já existe! Por favor escolha outro.");
		}
	}

	public void criaPosicao() {
		if (posicaoServ.existeCodPosicao(codPosicao)) {
			DisplayMessages
					.addErrorMessage("O Cód. de posição introduzido já existe! Por favor escolha outro.");
		} else {
			Posicao pos = new Posicao(dataAbertura, codPosicao, titulo, estado,
					quantidadeVagas, dataFecho, sla, responsavel, empresa,
					areaTecnica, descricao, canaisPublicacao, guiao);
			pos.setLocal(local);
			if (posicaoServ.createNewPosicao(pos) != null) {
				DisplayMessages.addInfoMessage("Posição criada com sucesso!");
				mail.enviaEmailSimples(
						"Nova posição atribuida",
						"Um administrador criou a nova posição "
								+ pos.getTitulo()
								+ " e definiu-o como Gestor desta posição!",
						pos.getResponsavel().getEmail());
			}
		}

	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public String getCodPosicao() {
		return codPosicao;
	}

	public void setCodPosicao(String codPosicao) {
		this.codPosicao = codPosicao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public EstadoPosicao getEstado() {
		return estado;
	}

	public void setEstado(EstadoPosicao estado) {
		this.estado = estado;
	}

	public int getQuantidadeVagas() {
		return quantidadeVagas;
	}

	public void setQuantidadeVagas(int quantidadeVagas) {
		this.quantidadeVagas = quantidadeVagas;
	}

	public Date getDataFecho() {
		return dataFecho;
	}

	public void setDataFecho(Date dataFecho) {
		this.dataFecho = dataFecho;
	}

	public int getSla() {
		return sla;
	}

	public void setSla(int sla) {
		this.sla = sla;
	}

	public ResponsavelPosicao getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(ResponsavelPosicao responsavel) {
		this.responsavel = responsavel;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public AreaTecnica getAreaTecnica() {
		return areaTecnica;
	}

	public void setAreaTecnica(AreaTecnica areaTecnica) {
		this.areaTecnica = areaTecnica;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCanaisPublicacao() {
		return canaisPublicacao;
	}

	public void setCanaisPublicacao(String canaisPublicacao) {
		this.canaisPublicacao = canaisPublicacao;
	}

	public Guiao getGuiao() {
		return guiao;
	}

	public void setGuiao(Guiao guiao) {
		this.guiao = guiao;
	}

	public List<ResponsavelPosicao> getResponsaveis() {
		return RESPONSAVEIS;
	}

	public List<LocalPosicao> getLocal() {
		return local;
	}

	public void setLocal(List<LocalPosicao> local) {
		this.local = local;
	}

	public List<Guiao> getGUIOES_DISPONIVEIS() {
		return GUIOES_DISPONIVEIS;
	}

}
