package pt.uc.dei.aor.pfinal.gestaocandidaturas.testes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.AdministradorService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.CandidatoService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.EntrevistadorService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.GestorService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.UtilizadorService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Administrador;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidato;
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

	@Inject
	private CandidatoService candidatoServ;

	private List<Utilizador> listaUtilizadores = new ArrayList<>();
	private List<Utilizador> listaUtilizadoresSemPerfil = new ArrayList<>();
	private List<Administrador> listaAdministradores = new ArrayList<>();
	private List<Gestor> listaGestores = new ArrayList<>();

	private List<Candidato> listaCandidatos = new ArrayList<>();

	private void criaUtilizadores(int quant) {
		for (int i = 1; i <= quant; i++) {
			System.out.println("utilizador " + i);
			Utilizador newUser = new Utilizador("user" + i, "123",
					"Utilizador " + i, "Apelido utilizador " + i,
					"email utilizador" + i);
			userService.createNewUtilizador(newUser);
		}
	}

	public void carregaUsers() {
		Utilizador user1 = new Utilizador("user", "123", "Utilizador teste",
				"Apelido utilizador teste", "mail1");
		userService.createNewUtilizador(user1);

		Administrador admin = new Administrador("admin", "123",
				"Administrador teste", "Apelido administrador teste",
				"mailadmin");
		adminService.createNewAdministrador(admin);

		Gestor gestor = new Gestor("gestor", "123", "Gestor teste",
				"Apelido gestor teste", "mailgest");
		gestService.createNewGestor(gestor);

		Entrevistador entr = new Entrevistador("ent", "123",
				"Entrevistador teste", "Apelido entrevistador teste", "mailent");
		entService.createNewEntrevistador(entr);

		Candidato cand = new Candidato();
		cand.setLogin("cand");
		cand.setEmail("mail cand");
		cand.setNome("candidato teste");
		cand.setPassword("123");
		candidatoServ.createNewCandidato(cand);
	}

	public void inicializaUtilizadores() {
		carregaUsers();
		criaUtilizadores(20);
		consultaListaUtilizadores();
	}

	public void apagaUtilizador2() {
		if (userService.deleteUtilizador(userService
				.getUtilizadorByLogin("user2")))
			System.out.println("Utilizador 2 apagado!");
		else
			System.out.println("Falha ao apagar o utilizador");
		consultaListaUtilizadores();
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
		consultaListaUtilizadores();
		consultaListaAdmins();
	}

	public void converterUserEmEntrevistador() {
		Utilizador user10 = userService.getUtilizadorByLogin("user10");
		Entrevistador ent10 = entService
				.convertUtilizadorInEntrevistador(user10.getId());
		System.out.println("user 10 convertido em entrevistador com o id:"
				+ ent10.getId());
		consultaListaUtilizadores();
	}

	public void converterUserEmGestor() {
		Utilizador user15 = userService.getUtilizadorByLogin("user15");
		Gestor gest15 = gestService.convertUtilizadorInGestor(user15.getId());
		System.out.println("user 15 convertido em gestor com o id:"
				+ gest15.getId());
		consultaListaUtilizadores();
		consultaListaGestores();
	}

	public void consultaListaAdmins() {
		List<Administrador> admins = adminService.listaAdministradores();
		System.out.println("Encontrados " + admins.size() + " administradores");
		listaAdministradores.clear();
		for (Administrador administrador : admins) {
			listaAdministradores.add(administrador);

		}
	}

	public void consultaListaUtilizadores() {
		List<Utilizador> users = userService.listaUtilizadores();
		System.out.println("Encontrados " + users.size() + " utilizadores");
		listaUtilizadores.clear();
		for (Utilizador user : users) {
			listaUtilizadores.add(user);

		}
		consultaListaUtilizadoresSemPerfil();
	}

	public void consultaListaGestores() {
		List<Gestor> gests = gestService.listaGestores();
		System.out.println("Encontrados " + gests.size() + " gestores");
		listaGestores.clear();
		for (Gestor administrador : gests) {
			listaGestores.add(administrador);

		}
	}

	public void consultaListaUtilizadoresSemPerfil() {
		List<Utilizador> users = userService.getUtilizadoresSemPerfil();
		listaUtilizadoresSemPerfil.clear();
		System.out.println("Encontrados " + users.size()
				+ " utilizadores sem perfil atribuido");
		for (Utilizador user : users) {
			listaUtilizadoresSemPerfil.add(user);

		}
	}

	public List<Utilizador> getListaUtilizadores() {
		return listaUtilizadores;
	}

	public void setListaUtilizadores(List<Utilizador> listaUtilizadores) {
		this.listaUtilizadores = listaUtilizadores;
	}

	public List<Administrador> getListaAdministradores() {
		return listaAdministradores;
	}

	public void setListaAdministradores(List<Administrador> listaAdministradores) {
		this.listaAdministradores = listaAdministradores;
	}

	public List<Gestor> getListaGestores() {
		return listaGestores;
	}

	public void setListaGestores(List<Gestor> listaGestores) {
		this.listaGestores = listaGestores;
	}

	public List<Candidato> getListaCandidatos() {
		return listaCandidatos;
	}

	public void setListaCandidatos(List<Candidato> listaCandidatos) {
		this.listaCandidatos = listaCandidatos;
	}

	public List<Utilizador> getListaUtilizadoresSemPerfil() {
		return listaUtilizadoresSemPerfil;
	}

	public void setListaUtilizadoresSemPerfil(
			List<Utilizador> listaUtilizadoresSemPerfil) {
		this.listaUtilizadoresSemPerfil = listaUtilizadoresSemPerfil;
	}
}
