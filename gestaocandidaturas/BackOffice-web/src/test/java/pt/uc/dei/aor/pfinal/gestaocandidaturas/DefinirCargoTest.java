package pt.uc.dei.aor.pfinal.gestaocandidaturas;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.AdministradorService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.EntrevistadorService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.GestorService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.UtilizadorService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Utilizador;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.view.DefinirCargo;


@RunWith(MockitoJUnitRunner.class)
public class DefinirCargoTest {
	@Mock
	UtilizadorService userService;
	
	@Mock
	Utilizador user;

	@Mock
	AdministradorService adminService;

	@Mock
	EntrevistadorService entService;

	@Mock
	GestorService gestService;
	
	@InjectMocks
	DefinirCargo cargo;
	
	@Test
	public void testListaUtilizadoresGetUsers() throws Exception{
		 Utilizador user1 = new Utilizador();
		 user1.setNome("Rita");
		 user1.setNome("Ana");
		 user1.setNome("Nuno");
		 
		 List<Utilizador> mostra = userService.listaUtilizadores();
		 
		 assertNotNull("nao deveria ser nula", mostra);
		 
	}
	
	@Test
	public void testListaUtilizadoresGetUmUtilizador() throws Exception{
		 Utilizador user1 = new Utilizador();
		 user1.setNome("");
		
		 
		 List<Utilizador> mostra = userService.listaUtilizadores();
		 
		 assertThat(mostra.getClass().getName(), is(equalTo("java.util.LinkedList")));
		 
		 
	}
}


