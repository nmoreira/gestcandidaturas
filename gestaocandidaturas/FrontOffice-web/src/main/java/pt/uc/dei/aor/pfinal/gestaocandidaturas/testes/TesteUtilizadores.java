package pt.uc.dei.aor.pfinal.gestaocandidaturas.testes;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.AdministradorService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.EntrevistadorService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.GestorService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.UtilizadorService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Administrador;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Entrevistador;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Gestor;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Utilizador;

@Named
@ViewScoped
public class TesteUtilizadores implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private UtilizadorService userService;

	@Inject
	private AdministradorService adminService;

	@Inject
	private EntrevistadorService entService;

	@Inject
	private GestorService gestService;

	private void criaUtilizadores(int quant) {
		for (int i = 1; i <= quant; i++) {
			System.out.println("utilizador " + i);
			Utilizador newUser = new Utilizador("user" + i,
					"password utilizador " + i, "Utilizador " + i,
					"Apelido utilizador " + i, "email utilizador" + i);
			userService.createNewUtilizador(newUser);
		}
	}

	public void inicializaUtilizadores() {
		criaUtilizadores(20);
	}

	public void apagaUtilizador2() {
		if (userService.deleteUtilizador(userService
				.getUtilizadorByLogin("user2")))
			System.out.println("Utilizador 2 apagado!");
		else
			System.out.println("Falha ao apagar o utilizador");
	}

	public void converterUserEmAdmin() {
		Utilizador user1 = userService.getUtilizadorByLogin("user1");

		Administrador admin1 = adminService
				.convertUtilizadorInAdministrador(user1.getId());
		System.out.println("user 1 convertido em administrador com o id:"
				+ admin1.getId());

		Utilizador user5 = userService.getUtilizadorByLogin("user5");
		Administrador admin5 = adminService
				.convertUtilizadorInAdministrador(user5.getId());
		System.out.println("user 5 convertido em administrador com o id:"
				+ admin5.getId());
	}

	public void converterUserEmEntrevistador() {
		Utilizador user10 = userService.getUtilizadorByLogin("user10");
		Entrevistador ent10 = entService
				.convertUtilizadorInEntrevistador(user10.getId());
		System.out.println("user 10 convertido em entrevistador com o id:"
				+ ent10.getId());
	}

	public void converterUserEmGestor() {
		Utilizador user15 = userService.getUtilizadorByLogin("user15");
		Gestor gest15 = gestService.convertUtilizadorInGestor(user15.getId());
		System.out.println("user 15 convertido em gestor com o id:"
				+ gest15.getId());
	}

	public void listaAdmins() {
		List<Administrador> admins = adminService.listaAdministradores();
		System.out.println("Encontrados " + admins.size() + " administradores");
		for (Administrador administrador : admins) {
			System.out.println(administrador);
		}
	}

	public void listaUtilizadores() {
		List<Utilizador> users = userService.listaUtilizadores();
		System.out.println("Encontrados " + users.size() + " utilizadores");
		for (Utilizador user : users) {
			System.out.println(user);
		}
	}

	public void listaGestores() {
		List<Gestor> gests = gestService.listaGestores();
		System.out.println("Encontrados " + gests.size() + " gestores");
		for (Gestor administrador : gests) {
			System.out.println(administrador);
		}
	}

	public void listaUtilizadoresSemPerfil() {
		List<Utilizador> users = userService.getUtilizadoresSemPerfil();
		System.out.println("Encontrados " + users.size()
				+ " utilizadores sem perfil atribuido");
		for (Utilizador user : users) {
			System.out.println(user);
		}
	}
}
