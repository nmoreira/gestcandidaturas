package pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Entrevistador
 *
 */
@Entity
@Table(name = "entrevistador")
public class Entrevistador extends Utilizador implements Serializable {

	@OneToMany(mappedBy = "entrevistador")
	private List<Entrevista> entrevistas;

	private static final long serialVersionUID = 1L;

	public Entrevistador() {
		super();
	}

	public List<Entrevista> getEntrevistas() {
		return entrevistas;
	}

	public void setEntrevistas(List<Entrevista> entrevistas) {
		this.entrevistas = entrevistas;
	}

}
