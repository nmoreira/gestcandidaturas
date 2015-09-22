package pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: GrupoEntrevistadores
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "GrupoEntrevistadores.findAll", query = "SELECT distinct g FROM GrupoEntrevistadores g left join fetch g.entrevistas"),
		@NamedQuery(name = "GrupoEntrevistadores.findById", query = "SELECT distinct g FROM GrupoEntrevistadores g left join fetch g.entrevistas WHERE g.id = :id") })
public abstract class GrupoEntrevistadores extends Utilizador {

	@OneToMany(mappedBy = "entrevistador")
	private List<Entrevista> entrevistas;

	public GrupoEntrevistadores() {
		super();
	}

	public GrupoEntrevistadores(String login, String password, String nome,
			String apelido, String email) {
		super(login, password, nome, apelido, email);
	}

	public List<Entrevista> getEntrevistas() {
		return entrevistas;
	}

	public void setEntrevistas(List<Entrevista> entrevistas) {
		this.entrevistas = entrevistas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (this.getId() ^ (this.getId() >>> 32));
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
		GrupoEntrevistadores other = (GrupoEntrevistadores) obj;
		if (this.getId() != other.getId())
			return false;
		return true;
	}

}
