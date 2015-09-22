package pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Entrevistador
 *
 */
@Entity
@Table(name = "entrevistador")
@NamedQueries({
		@NamedQuery(name = "Entrevistador.findAll", query = "SELECT a FROM Entrevistador a"),
		@NamedQuery(name = "Entrevistador.findByLogin", query = "SELECT a FROM Entrevistador a WHERE a.login = :login"),
		@NamedQuery(name = "Entrevistador.findByEmail", query = "SELECT a FROM Entrevistador a WHERE a.email = :email"),
		@NamedQuery(name = "Entrevistador.findById", query = "SELECT a FROM Entrevistador a WHERE a.id = :id") })
public class Entrevistador extends GrupoEntrevistadores implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Entrevistador() {
		super();
		this.setCargo("ENTREVISTADOR");
	}

	public Entrevistador(Utilizador user) {
		super();
		this.setCargo("ENTREVISTADOR");
		this.setNome(user.getNome());
		this.setApelido(user.getApelido());
		this.setEmail(user.getEmail());
		this.setPassword(user.getPassword());
		this.setLogin(user.getLogin());
	}

	public Entrevistador(String login, String password, String nome,
			String apelido, String email) {
		super(login, password, nome, apelido, email);
		this.setCargo("ENTREVISTADOR");
	}

	public List<Entrevista> getEntrevistas() {
		return super.getEntrevistas();

	}

	public void setEntrevistas(List<Entrevista> entrevistas) {
		super.setEntrevistas(entrevistas);
	}

}
