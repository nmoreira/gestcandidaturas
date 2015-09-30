package pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;


import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.*;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.CandidatoService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.UtilizadorService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidato;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.view.NovoCandidato;

@RunWith(MockitoJUnitRunner.class)
public class NovoCandidatoTest {
	
	@Mock
	javax.persistence.Query q;
	
	@Mock
	EntityManager em;
	
	@Mock
	Candidato c;
	
	@Mock
	CandidatoService candService;
	
	@Mock
	UtilizadorService utilService;
	
	@InjectMocks
	NovoCandidato novo;


	@Test
	public void sugerirPaisTest() throws Exception{
		String pais1 = "Portugal";
		String pais2 = "Croácia";
		String pais3 = "Alemanha";
		String pais4 = "Brasil";
		
	List<String> paisesugeridos = new ArrayList<>();
		
	 assertNotNull("nao deveria ser nula", paisesugeridos);
		
	}
	
	@Test
	public void testanovocandidato() throws Exception{
	
	String login ="";
	String password=""; 
	String nome="";
	String apelido="";
	String email="";
	String morada="";
	String cidade="";
	
	long telefone=0;
	long telemovel=0;
	String pais="";
	String curso="";
	String escola="";
	String cv="";
	String idLinkedin="";
	
	Candidato newUser = new Candidato(login, password, nome, apelido,
			email, morada, cidade, telefone, telemovel, pais, curso,
			escola, cv, idLinkedin);
	
	when(candService.createNewCandidato(newUser)).thenReturn(true);
	
	}

	@Test
	public void testaatualizarCandidato() throws Exception{
	
	String login ="";
	String password=""; 
	String nome="";
	String apelido="";
	String email="";
	String morada="";
	String cidade="";
	
	long telefone=0;
	long telemovel=0;
	String pais="";
	String curso="";
	String escola="";
	String cv="";
	String idLinkedin="";
	
	Candidato newUser = new Candidato(login, password, nome, apelido,
			email, morada, cidade, telefone, telemovel, pais, curso,
			escola, cv, idLinkedin);
	

	
	when(candService.atualizarCandidato(newUser)).thenReturn(true);
	
	}
	
	
}
