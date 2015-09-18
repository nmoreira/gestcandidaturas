package pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.TipoPergunta;

/**
 * Entity implementation class for Entity: Questao
 *
 */
@Entity
@Table(name = "questao")
@NamedQueries({
		@NamedQuery(name = "Questao.findAll", query = "SELECT q FROM Questao q"),
		@NamedQuery(name = "Questao.findById", query = "SELECT q FROM Questao q WHERE q.id = :id") })
public abstract class Questao implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private long id;

	@ManyToMany(mappedBy = "questoes")
	private List<Guiao> guioes;

	@ManyToMany(mappedBy = "questoesEntrevista")
	private List<Entrevista> entrevistas;

	@Column
	@Enumerated(EnumType.STRING)
	private TipoPergunta tipo;

	@Column
	private int ordem;

	@Column
	private String pergunta;

	// TODO falta definir o tipo de questões

	private static final long serialVersionUID = 1L;

	public Questao() {
		super();
	}

	public abstract Questao copiaPergunta(Questao pergunta);

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Guiao> getGuioes() {
		return guioes;
	}

	public void setGuioes(List<Guiao> guioes) {
		this.guioes = guioes;
	}

	public int getOrdem() {
		return ordem;
	}

	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public TipoPergunta getTipo() {
		return tipo;
	}

	public void setTipo(TipoPergunta tipo) {
		this.tipo = tipo;
	}

	public List<Entrevista> getEntrevistas() {
		return entrevistas;
	}

	public void setEntrevistas(List<Entrevista> entrevistas) {
		this.entrevistas = entrevistas;
	}

}
