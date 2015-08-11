package pt.uc.dei.aor.pfinal.gestaocandidaturas.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.PosicaoService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.ResponsavelPosicaoService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Guiao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Posicao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.ResponsavelPosicao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.DisplayMessages;

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
	private String localizacao;
	private String estado;
	private int quantidadeVagas;
	private Date dataFecho;
	private String sla;
	private ResponsavelPosicao responsavel;
	private String empresa;
	private String areaTecnica;
	private String descricao;
	private String canaisPublicacao;
	private Guiao guiao;

	private static final List<ResponsavelPosicao> RESPONSAVEIS = new ArrayList<>();

	@Inject
	private PosicaoService posicaoServ;

	@Inject
	private ResponsavelPosicaoService respServ;

	/**
	 * Default constructor.
	 */
	public NovaPosicao() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	private void loadResponsaveis() {
		List<ResponsavelPosicao> list = respServ.getAllResponsaveisPosicao();
		for (ResponsavelPosicao responsavelPosicao : list) {
			RESPONSAVEIS.add(responsavelPosicao);
		}
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
			Posicao pos = new Posicao(dataAbertura, codPosicao, titulo,
					localizacao, estado, quantidadeVagas, dataFecho, sla,
					responsavel, empresa, areaTecnica, descricao,
					canaisPublicacao, guiao);
			posicaoServ.createNewPosicao(pos);
			DisplayMessages.addInfoMessage("Posição criada com sucesso!");
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

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
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

	public String getSla() {
		return sla;
	}

	public void setSla(String sla) {
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

	public String getAreaTecnica() {
		return areaTecnica;
	}

	public void setAreaTecnica(String areaTecnica) {
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

	public static List<ResponsavelPosicao> getResponsaveis() {
		return RESPONSAVEIS;
	}

}
