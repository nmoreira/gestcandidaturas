package pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.mockito.runners.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.UtilizadorService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidato;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Utilizador;


@RunWith(MockitoJUnitRunner.class)
public class CandidatoTest {

	@Mock
	Query q;
	
	@Mock
	EntityManager em;
	
	@Mock
	Utilizador util;
	
	@Mock
	UtilizadorService utilService;
	
	@InjectMocks
	Candidato novo;
	
	
	@Test
	public void testaEncontraCandidatoLogin(){

		
		when(em.createQuery("from Candidato novo WHERE novo.login = :login")).thenReturn(q);
		novo.getLogin();
		
		}
	
	
	@Test
	public void testaGetUtilizadorFromId(){
		
		when(em.createQuery("from Utilizador novo where novo.id = :id")).thenReturn(q);
		novo.getId();
		
		}
	
	@Test
	public void testaEncontraCandidatoEmail(){
	
		
		when(em.createQuery("from Candidato novo WHERE novo.email = :email")).thenReturn(q);
		novo.getEmail();
		
		}
	
}

