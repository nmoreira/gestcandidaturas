package pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Guiao
 *
 */
@Entity
@Table(name = "guiao")
@NamedQueries({
		@NamedQuery(name = "Guiao.findAll", query = "SELECT g FROM Guiao g"),
		@NamedQuery(name = "Guiao.findById", query = "SELECT g FROM Guiao g WHERE g.id = :id") })
public class Guiao implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private long id;

	@Column
	private String nome;

	@Column
	private boolean disponivel;

	@OneToMany(mappedBy = "guiao")
	private List<Posicao> posicoes;

	@ManyToMany
	private List<Questao> questoes;

	private static final long serialVersionUID = 1L;

	public Guiao() {
		super();
		this.disponivel = true;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public List<Posicao> getPosicoes() {
		return posicoes;
	}

	public void setPosicoes(List<Posicao> posicoes) {
		this.posicoes = posicoes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Guiao other = (Guiao) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
