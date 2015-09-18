package pt.uc.dei.aor.pfinal.gestaocandidaturas.view;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.UtilizadorService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Utilizador;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.DisplayMessages;

@Named
@ViewScoped
public class NovoUtilizador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String login;
	private String password;
	private String nome;
	private String apelido;
	private String email;

	@Inject
	private UtilizadorService userService;

	public void criaUtilizador() {
		if (userService.existeEmail(email) == true) {
			DisplayMessages.addErrorMessage("O email " + email
					+ " já está registado!");
		} else if (userService.existeLogin(login) == true) {
			DisplayMessages.addErrorMessage("O login " + login
					+ " já está em uso! Por favor escolha outro!");
		} else {
			Utilizador newUser = new Utilizador(login, password, nome, apelido,
					email);
			userService.createNewUtilizador(newUser);
			DisplayMessages.addInfoMessage("Novo Utilizador criado!");
			DisplayMessages
					.addInfoMessage("Aguarde que um administrador lhe atribua o perfil correto!");
		}
	}

	public void validaLogin() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (userService.existeLogin(login) == true)
			DisplayMessages.addErrorMessage("O login " + login
					+ " já está em uso! Por favor escolha outro");
		else
			DisplayMessages.addInfoMessage("Login " + login + " disponível");
	}

	public void validaEmail() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (userService.existeEmail(email) == true)
			DisplayMessages.addErrorMessage("O email " + email
					+ " já está registado!");
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

	public void setApelido(String sobrenome) {
		this.apelido = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
