package pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Guiao
 *
 */
@Entity
@Table(name = "guiao")
public class Guiao implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private long id;

	@OneToMany(mappedBy = "guiao")
	private List<Posicao> entrevistas;

	@ManyToMany
	private List<Questao> questoes;

	private static final long serialVersionUID = 1L;

	public Guiao() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}

	public List<Posicao> getEntrevistas() {
		return entrevistas;
	}

	public void setEntrevistas(List<Posicao> entrevistas) {
		this.entrevistas = entrevistas;
	}

}
