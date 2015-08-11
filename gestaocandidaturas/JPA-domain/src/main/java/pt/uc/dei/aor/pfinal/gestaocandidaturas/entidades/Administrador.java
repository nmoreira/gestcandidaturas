package pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Administrador
 *
 */
@Entity
@Table(name = "administrador")
@NamedQueries({
		@NamedQuery(name = "Administrador.findAll", query = "SELECT a FROM Administrador a"),
		@NamedQuery(name = "Administrador.findByLogin", query = "SELECT a FROM Administrador a WHERE a.login = :login"),
		@NamedQuery(name = "Administrador.findByEmail", query = "SELECT a FROM Administrador a WHERE a.email = :email"),
		@NamedQuery(name = "Administrador.findById", query = "SELECT a FROM Administrador a WHERE a.id = :id") })
public class Administrador extends ResponsavelPosicao implements Serializable {

	private static final long serialVersionUID = 1L;

	public Administrador() {
		super();
		this.setCargo("ADMIN");
	}

	public Administrador(Utilizador user) {
		super();
		this.setCargo("ADMIN");
		this.setNome(user.getNome());
		this.setApelido(user.getApelido());
		this.setEmail(user.getEmail());
		this.setPassword(user.getPassword());
		this.setLogin(user.getLogin());
	}

	public Administrador(String login, String password, String nome,
			String apelido, String email) {
		super(login, password, nome, apelido, email);
		this.setCargo("ADMIN");
	}

}
