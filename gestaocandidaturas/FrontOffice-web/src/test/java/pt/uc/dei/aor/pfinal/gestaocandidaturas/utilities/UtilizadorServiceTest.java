package pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import javax.management.Query;
import javax.persistence.EntityManager;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.UtilizadorService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidato;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Perfil;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Utilizador;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.CandidatoFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.UtilizadorFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.view.NovoUtilizador;

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

		String email="";	
		ufacade.findByEmail(email);
		assertThat(c.getEmail(), is(equalTo(null)));		

	}
	
	@Test
	public void testAlteraPassword() throws Exception{

		String passnova ="";
		ufacade.changePassword(0, passnova);
		assertThat(c.getPassword(), is(equalTo(null)));		

	}
}
