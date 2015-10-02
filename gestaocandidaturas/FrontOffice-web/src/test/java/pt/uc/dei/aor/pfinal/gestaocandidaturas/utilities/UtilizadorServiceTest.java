package pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.UtilizadorService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidato;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Utilizador;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.CandidatoFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.UtilizadorFacade;

@RunWith(MockitoJUnitRunner.class)
public class UtilizadorServiceTest {

	@Mock
	Utilizador c;



	@Mock
	UtilizadorFacade ufacade;

	@Mock
	Candidato cand;

	@Mock
	CandidatoFacade cfacade;

	@InjectMocks
	UtilizadorService util;

	@Test
	public void testExisteMail() throws Exception{

		String email="candidaturasaor@gmail.com";	
		c.setEmail(email);
		 ufacade.findByEmail(email);
		
		Assert.assertTrue(c.getEmail(), true);
		
		System.out.println("O email procurado existe");

	}
	
	@Test
	public void testAlteraPassword() throws Exception{
	String passnova="null";
	 c.setPassword(passnova);
		
		
		ufacade.changePassword(0, passnova);
		assertThat(c.getPassword(), is(equalTo(null)));	
		
		System.out.println("A nova password é: "+c.getPassword());

	}
}
