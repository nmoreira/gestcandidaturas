package pt.uc.dei.aor.pfinal.gestaocandidaturas.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
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
public class ListaUtilizadores implements Serializable {

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

	private List<Utilizador> listaTodosUtilizadores;
	private List<Utilizador> listaUtilizadoresSemPerfil;
	private List<Candidato> listaCandidatos;
	private List<Administrador> listaAdministradores;
	private List<Gestor> listaGestores;
	private List<Entrevistador> listaEntrevistadores;

	@PostConstruct
	private void init() {
		listaTodosUtilizadores = userService.listaUtilizadores();
		listaUtilizadoresSemPerfil = userService.getUtilizadoresSemPerfil();
		listaCandidatos = candidatoServ.getTodosCandidatos();
		listaAdministradores = adminService.listaAdministradores();
		listaGestores = gestService.listaGestores();
		listaEntrevistadores = entService.getTodosOsEntrevistadores();
	}

	public List<Utilizador> getListaTodosUtilizadores() {
		return listaTodosUtilizadores;
	}

	public void setListaTodosUtilizadores(
			List<Utilizador> listaTodosUtilizadores) {
		this.listaTodosUtilizadores = listaTodosUtilizadores;
	}

	public List<Utilizador> getListaUtilizadoresSemPerfil() {
		return listaUtilizadoresSemPerfil;
	}

	public void setListaUtilizadoresSemPerfil(
			List<Utilizador> listaUtilizadoresSemPerfil) {
		this.listaUtilizadoresSemPerfil = listaUtilizadoresSemPerfil;
	}

	public List<Candidato> getListaCandidatos() {
		return listaCandidatos;
	}

	public void setListaCandidatos(List<Candidato> listaCandidatos) {
		this.listaCandidatos = listaCandidatos;
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

	public List<Entrevistador> getListaEntrevistadores() {
		return listaEntrevistadores;
	}

	public void setListaEntrevistadores(List<Entrevistador> listaEntrevistadores) {
		this.listaEntrevistadores = listaEntrevistadores;
	}

}
