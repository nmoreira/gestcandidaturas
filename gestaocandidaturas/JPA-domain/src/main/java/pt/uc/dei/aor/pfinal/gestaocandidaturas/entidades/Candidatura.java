package pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: Candidatura
 *
 */
@Entity
@Table(name = "candidatura")
@NamedQueries({
		@NamedQuery(name = "Candidatura.findAll", query = "SELECT c FROM Candidatura c"),
		@NamedQuery(name = "Candidatura.findById", query = "SELECT c FROM Candidatura c WHERE c.id = :id"),
		@NamedQuery(name = "Candidatura.findByCandidato", query = "SELECT c FROM Candidatura c WHERE c.candidato = :candidato"),
		@NamedQuery(name = "Candidatura.findByPosicao", query = "SELECT c FROM Candidatura c WHERE c.posicao = :posicao") })
public class Candidatura implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private long id;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCandidatura;

	@Column
	private String cartaMotivacao;

	@Column
	private String fonte;

	@ManyToOne
	private Posicao posicao;

	@ManyToOne
	private Candidato candidato;

	@OneToMany(mappedBy = "candidatura")
	private List<Entrevista> entrevistas;

	private static final long serialVersionUID = 1L;

	public Candidatura() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDataCandidatura() {
		return dataCandidatura;
	}

	public void setDataCandidatura(Date dataCandidatura) {
		this.dataCandidatura = dataCandidatura;
	}

	public String getCartaMotivacao() {
		return cartaMotivacao;
	}

	public void setCartaMotivacao(String cartaMotivacao) {
		this.cartaMotivacao = cartaMotivacao;
	}

	public String getFonte() {
		return fonte;
	}

	public void setFonte(String fonte) {
		this.fonte = fonte;
	}

	public Posicao getPosicao() {
		return posicao;
	}

	public void setPosicao(Posicao posicao) {
		this.posicao = posicao;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public List<Entrevista> getEntrevistas() {
		return entrevistas;
	}

	public void setEntrevistas(List<Entrevista> entrevistas) {
		this.entrevistas = entrevistas;
	}

}
