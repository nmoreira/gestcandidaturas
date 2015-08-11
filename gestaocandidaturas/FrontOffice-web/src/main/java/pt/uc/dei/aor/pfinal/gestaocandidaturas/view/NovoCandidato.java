package pt.uc.dei.aor.pfinal.gestaocandidaturas.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.CandidatoService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.UtilizadorService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidato;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.DisplayMessages;

@Named
@ViewScoped
public class NovoCandidato implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String login;
	private String password;
	private String nome;
	private String apelido;
	private String email;
	private String morada;
	private String cidade;
	private long telefone;
	private long telemovel;
	private String pais;
	private String curso;
	private String escola;
	private String cv;
	private String idLinkedin;

	private List<String> paises = new ArrayList<>();

	public NovoCandidato() {
		paises.add("Portugal");
		paises.add("Alemanha");
		paises.add("Espanha");
		paises.add("França");
		paises.add("Suiça");
		paises.add("Argélia");
		paises.add("Brasil");
		paises.add("Argentina");
		paises.add("Bolívia");
		paises.add("Estados Unidos");
		paises.add("Bulgária");
	}

	@Inject
	private CandidatoService candidatoServ;

	@Inject
	private UtilizadorService userServ;

	public void criaCandidato() {
		if (userServ.existeEmail(email) == true) {
			DisplayMessages.addErrorMessage("O email " + email
					+ " já está registado!");
		} else if (userServ.existeLogin(login) == true) {
			DisplayMessages.addErrorMessage("O login " + login
					+ " já está em uso! Por favor escolha outro");
		} else {
			Candidato newUser = new Candidato(login, password, nome, apelido,
					email, morada, cidade, telefone, telemovel, pais, curso,
					escola, cv, idLinkedin);
			candidatoServ.createNewCandidato(newUser);
			DisplayMessages.addInfoMessage("Candidato " + nome
					+ " registado com sucesso");
		}
	}

	public void validaLogin() {
		if (userServ.existeLogin(login) == true)
			DisplayMessages.addErrorMessage("O login " + login
					+ " já está em uso! Por favor escolha outro");
		else
			DisplayMessages.addInfoMessage("Login " + login + " disponível");
	}

	public void validaEmail() {
		if (userServ.existeEmail(email) == true)
			DisplayMessages.addErrorMessage("O email " + email
					+ " já está registado!");
	}

	public List<String> sugerirpais(String consulta) {
		List<String> paisesugeridos = new ArrayList<>();
		for (String pais : paises) {
			if (pais.toLowerCase().startsWith(consulta.toLowerCase()))
				paisesugeridos.add(pais);
		}
		return paisesugeridos;
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
