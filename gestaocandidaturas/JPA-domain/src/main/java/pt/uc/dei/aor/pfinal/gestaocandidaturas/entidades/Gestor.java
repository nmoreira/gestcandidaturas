package pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Gestor
 *
 */
@Entity
@Table(name = "gestor")
@NamedQueries({
		@NamedQuery(name = "Gestor.findAll", query = "SELECT a FROM Gestor a"),
		@NamedQuery(name = "Gestor.findByLogin", query = "SELECT a FROM Gestor a WHERE a.login = :login"),
		@NamedQuery(name = "Gestor.findByEmail", query = "SELECT a FROM Gestor a WHERE a.email = :email"),
		@NamedQuery(name = "Gestor.findById", query = "SELECT a FROM Gestor a WHERE a.id = :id") })
public class Gestor extends ResponsavelPosicao implements Serializable {

	private static final long serialVersionUID = 1L;

	public Gestor() {
		super();
		this.setCargo("GESTOR");
	}

	public Gestor(Utilizador user) {
		super();
		this.setCargo("GESTOR");
		this.setNome(user.getNome());
		this.setApelido(user.getApelido());
		this.setEmail(user.getEmail());
		this.setPassword(user.getPassword());
		this.setLogin(user.getLogin());
	}

	public Gestor(String login, String password, String nome, String apelido,
			String email) {
		super(login, password, nome, apelido, email);
		this.setCargo("GESTOR");
	}

}
