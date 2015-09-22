package pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: Entrevista
 *
 */
@Entity
@Table(name = "entrevista")
@NamedQueries({
		@NamedQuery(name = "Entrevista.findAll", query = "SELECT e FROM Entrevista e"),
		@NamedQuery(name = "Entrevista.findById", query = "SELECT e FROM Entrevista e WHERE e.id = :id"),
		@NamedQuery(name = "Entrevista.findByCandidatura", query = "SELECT e FROM Entrevista e WHERE e.candidatura = :candidatura"),
		@NamedQuery(name = "Entrevista.findByEntrevistador", query = "SELECT e FROM Entrevista e WHERE e.entrevistador = :entrevistador"),
		@NamedQuery(name = "Entrevista.findByData", query = "SELECT e FROM Entrevista e WHERE e.dataEntrevista = :data") })
public class Entrevista implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private long id;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEntrevista;

	@ManyToOne
	private GrupoEntrevistadores entrevistador;

	@ManyToOne
	private Candidatura candidatura;

	@ManyToMany
	private List<Questao> questoesEntrevista;

	@OneToOne
	private Feedback feedback;

	private static final long serialVersionUID = 1L;

	public Entrevista() {
		super();
	}

	public Entrevista(Date dataEntrevista, GrupoEntrevistadores entrevistador,
			Candidatura candidatura) {
		super();
		this.dataEntrevista = dataEntrevista;
		this.entrevistador = entrevistador;
		this.candidatura = candidatura;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDataEntrevista() {
		return dataEntrevista;
	}

	public void setDataEntrevista(Date dataEntrevista) {
		this.dataEntrevista = dataEntrevista;
	}

	public GrupoEntrevistadores getEntrevistador() {
		return entrevistador;
	}

	public void setEntrevistador(GrupoEntrevistadores entrevistador) {
		this.entrevistador = entrevistador;
	}

	public Candidatura getCandidatura() {
		return candidatura;
	}

	public void setCandidatura(Candidatura candidatura) {
		this.candidatura = candidatura;
	}

	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

	public List<Questao> getQuestoesEntrevista() {
		return questoesEntrevista;
	}

	public void setQuestoesEntrevista(List<Questao> questoesEntrevista) {
		this.questoesEntrevista = questoesEntrevista;
	}

}
