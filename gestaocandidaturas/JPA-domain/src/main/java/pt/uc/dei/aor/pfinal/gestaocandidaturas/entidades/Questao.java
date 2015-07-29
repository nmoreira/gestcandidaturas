package pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Questao
 *
 */
@Entity
@Table(name = "questao")
public class Questao implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private long id;

	@ManyToMany(mappedBy = "questoes")
	private List<Guiao> guioes;

	// TODO falta definir o tipo de questões

	private static final long serialVersionUID = 1L;

	public Questao() {
		super();
	}

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

}
