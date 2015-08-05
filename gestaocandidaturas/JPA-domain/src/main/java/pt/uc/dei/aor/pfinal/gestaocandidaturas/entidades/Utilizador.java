package pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

//@MappedSuperclass
@Entity
// @DiscriminatorColumn(name = "Tipo")
// @Inheritance(strategy = InheritanceType.JOINED)
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
		@NamedQuery(name = "Utilizador.findAll", query = "SELECT u FROM Utilizador u"),
		@NamedQuery(name = "Utilizador.findByLogin", query = "SELECT u FROM Utilizador u WHERE u.login = :login"),
		@NamedQuery(name = "Utilizador.findByEmail", query = "SELECT u FROM Utilizador u WHERE u.email = :email"),
		@NamedQuery(name = "Utilizador.findByCargo", query = "SELECT u FROM Utilizador u WHERE u.cargo = :cargo"),
		@NamedQuery(name = "Utilizador.findById", query = "SELECT u FROM Utilizador u WHERE u.id = :id") })
public class Utilizador {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private long id;

	@Column
	private String login;

	@Column
	private String password;

	@Column
	private String nome;

	@Column
	private String apelido;

	@Column(unique = true)
	private String email;

	@Column
	private String cargo;

	@ManyToOne
	private Perfil perfil;

	public Utilizador() {
		// TODO Auto-generated constructor stub
	}

	public Utilizador(String login, String password, String nome,
			String apelido, String email) {
		super();
		this.login = login;
		this.password = password;
		this.nome = nome;
		this.apelido = apelido;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

}
