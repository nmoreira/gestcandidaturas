package pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Candidato
 *
 */
@Entity
@Table(name = "candidato")
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

	public Candidato() {
		super();
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

}
