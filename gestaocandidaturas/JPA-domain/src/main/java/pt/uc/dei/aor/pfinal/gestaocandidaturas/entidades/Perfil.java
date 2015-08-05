package pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Perfil
 *
 */
@Entity
@Table(name = "perfil")
@NamedQueries({
		@NamedQuery(name = "Perfil.findAll", query = "SELECT p FROM Perfil p"),
		@NamedQuery(name = "Perfil.findByName", query = "SELECT p FROM Perfil p WHERE p.cargo = :cargo"),
		@NamedQuery(name = "Perfil.findById", query = "SELECT p FROM Perfil p WHERE p.id = :id") })
public class Perfil implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private long id;

	@Column
	private String cargo;

	@OneToMany(mappedBy = "perfil")
	private List<Utilizador> utilizadores;

	public Perfil() {
		super();
	}

	public Perfil(String cargo) {
		this.cargo = cargo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public List<Utilizador> getUtilizadores() {
		return utilizadores;
	}

	public void setUtilizadores(List<Utilizador> utilizador) {
		this.utilizadores = utilizador;
	}

}
