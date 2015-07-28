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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Candidatura
 *
 */
@Entity
@Table(name = "candidatura")
public class Candidatura implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private long id;

	@Column
	private Date dataCandidatura;

	@Column
	private String cartaMotivacao;

	@Column
	private String fonte;

	@ManyToOne
	private Posicao posicao;

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

}
