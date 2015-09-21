package pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.AreaTecnica;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.EstadoPosicao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.LocalPosicao;

/**
 * Entity implementation class for Entity: Posicao
 *
 */
@Entity
@Table(name = "posicao")
@NamedQueries({
		@NamedQuery(name = "Posicao.findAll", query = "SELECT DISTINCT p FROM Posicao p left join fetch p.local"),
		@NamedQuery(name = "Posicao.findByTitulo", query = "SELECT p FROM Posicao p WHERE p.titulo LIKE :titulo"),
		@NamedQuery(name = "Posicao.findByEmpresa", query = "SELECT p FROM Posicao p WHERE p.empresa LIKE :empresa"),
		@NamedQuery(name = "Posicao.findById", query = "SELECT DISTINCT p FROM Posicao p left join fetch p.local WHERE p.id = :id"),
		@NamedQuery(name = "Posicao.findByCodPosicao", query = "SELECT p FROM Posicao p WHERE p.codPosicao = :codPosicao"),
		@NamedQuery(name = "Posicao.findByDataAbertura", query = "SELECT p FROM Posicao p WHERE p.dataAbertura = :dataAbertura") })
public class Posicao implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private long id;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAbertura;

	@Column(unique = true)
	@NotNull
	private String codPosicao;

	@Column
	private String titulo;

	@Column
	private String localizacao;

	@ElementCollection
	@CollectionTable(name = "posicao_locais", joinColumns = @JoinColumn(name = "posicao_id"))
	@Column(name = "local")
	// @CollectionTable(name = "localizacao_posicao",
	// joinColumns = @JoinColumn(name = "posicao_id"))
	// @Column(name = "local")
	// @Transient
	private List<String> local = new ArrayList<>();

	@Column
	@Enumerated(EnumType.STRING)
	private EstadoPosicao estado;

	@Column
	private int quantidadeVagas;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFecho;

	@Column
	private String sla;

	@ManyToOne
	private ResponsavelPosicao responsavel;

	@Column
	private String empresa;

	@Column
	@Enumerated(EnumType.STRING)
	private AreaTecnica areaTecnica;

	@Column(columnDefinition = "text")
	private String descricao;

	@Column
	private String canaisPublicacao;

	@ManyToOne
	private Guiao guiao;

	@OneToMany(mappedBy = "posicao")
	private List<Candidatura> candidaturas;

	private static final long serialVersionUID = 1L;

	public Posicao() {
		super();
	}

	public Posicao(Date dataAbertura, String codPosicao, String titulo,
			String localizacao, EstadoPosicao estado, int quantidadeVagas,
			Date dataFecho, String sla, ResponsavelPosicao responsavel,
			String empresa, AreaTecnica areaTecnica, String descricao,
			String canaisPublicacao, Guiao guiao) {
		super();
		this.dataAbertura = dataAbertura;
		this.codPosicao = codPosicao;
		this.titulo = titulo;
		this.localizacao = localizacao;
		this.estado = estado;
		this.quantidadeVagas = quantidadeVagas;
		this.dataFecho = dataFecho;
		this.sla = sla;
		this.responsavel = responsavel;
		this.empresa = empresa;
		this.areaTecnica = areaTecnica;
		this.descricao = descricao;
		this.canaisPublicacao = canaisPublicacao;
		this.guiao = guiao;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public List<LocalPosicao> getLocal() {
		List<LocalPosicao> loc = new ArrayList<>();
		for (String locaisBD : local) {
			for (LocalPosicao localPosicao : LocalPosicao.values()) {
				if (locaisBD.equals(localPosicao.toString())) {
					loc.add(localPosicao);
					break;
				}
			}
		}
		return loc;
	}

	public void setLocal(List<LocalPosicao> local) {
		List<String> loc = new ArrayList<>();
		for (int i = 0; i < local.size(); i++) {
			LocalPosicao l = local.get(i);
			loc.add(l.toString());
		}
		this.local = loc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codPosicao == null) ? 0 : codPosicao.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posicao other = (Posicao) obj;
		if (codPosicao == null) {
			if (other.codPosicao != null)
				return false;
		} else if (!codPosicao.equals(other.codPosicao))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	public Guiao getGuiao() {
		return guiao;
	}

	public void setGuiao(Guiao guiao) {
		this.guiao = guiao;
	}

	public List<Candidatura> getCandidaturas() {
		return candidaturas;
	}

	public void setCandidaturas(List<Candidatura> candidaturas) {
		this.candidaturas = candidaturas;
	}

}
