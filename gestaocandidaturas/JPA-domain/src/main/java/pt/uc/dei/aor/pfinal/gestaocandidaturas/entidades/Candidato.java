package pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Candidato
 *
 */
@Entity
@Table(name = "candidato")
@NamedQueries({
		@NamedQuery(name = "Candidato.findAll", query = "SELECT a FROM Candidato a"),
		@NamedQuery(name = "Candidato.findByLogin", query = "SELECT a FROM Candidato a WHERE a.login = :login"),
		@NamedQuery(name = "Candidato.findByEmail", query = "SELECT a FROM Candidato a WHERE a.email = :email"),
		@NamedQuery(name = "Candidato.findById", query = "SELECT a FROM Candidato a WHERE a.id = :id") })
public class Candidato extends Utilizador implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column
	private String morada;

	@Column
	private String cidade;

	@Column
	private long telefone;

	@Column
	private long telemovel;

	@Column
	private String pais;

	@Column
	private String curso;

	@Column
	private String escola;

	@Column
	private String cv;

	@Column
	private String idLinkedin;

	@OneToMany(mappedBy = "candidato")
	private List<Candidatura> candidaturas;

	@ElementCollection
	private List<String> cartas = new ArrayList<>();

	public Candidato() {
		super();
		this.setCargo("CANDIDATO");
	}

	public Candidato(Utilizador user) {
		super();
		this.setCargo("CANDIDATO");
		this.setNome(user.getNome());
		this.setApelido(user.getApelido());
		this.setEmail(user.getEmail());
		this.setPassword(user.getPassword());
		this.setLogin(user.getLogin());
	}

	public Candidato(String login, String password, String nome,
			String apelido, String email, String morada, String cidade,
			long telefone, long telemovel, String pais, String curso,
			String escola, String cv, String idLinkedin) {
		super(login, password, nome, apelido, email);
		this.setCargo("CANDIDATO");
		this.morada = morada;
		this.cidade = cidade;
		this.telefone = telefone;
		this.telemovel = telemovel;
		this.pais = pais;
		this.curso = curso;
		this.escola = escola;
		this.cv = cv;
		this.idLinkedin = idLinkedin;
	}

	public String getMorada() {
		return morada;
	}

	public void setMorada(String morada) {
		this.morada = morada;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public long getTelefone() {
		return telefone;
	}

	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}

	public long getTelemovel() {
		return telemovel;
	}

	public void setTelemovel(long telemovel) {
		this.telemovel = telemovel;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getEscola() {
		return escola;
	}

	public void setEscola(String escola) {
		this.escola = escola;
	}

	public String getCv() {
		return cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}

	public String getIdLinkedin() {
		return idLinkedin;
	}

	public void setIdLinkedin(String idLinkedin) {
		this.idLinkedin = idLinkedin;
	}

	public List<Candidatura> getCandidaturas() {
		return candidaturas;
	}

	public void setCandidaturas(List<Candidatura> candidaturas) {
		this.candidaturas = candidaturas;
	}

	public List<String> getCartas() {
		return cartas;
	}

	public void setCartas(List<String> cartasMotivacao) {
		this.cartas = cartasMotivacao;
	}
}
