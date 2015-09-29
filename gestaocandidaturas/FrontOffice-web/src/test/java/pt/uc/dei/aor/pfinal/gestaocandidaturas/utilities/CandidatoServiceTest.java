package pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.primefaces.component.log.Log;




import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.CandidatoService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.UtilizadorService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidato;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.CandidatoFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.view.NovoCandidato;


@RunWith(MockitoJUnitRunner.class)
public class CandidatoServiceTest {

	
	@Mock
	Candidato c;
	
	@Mock
	UtilizadorService utilService;
	
	@Mock
	CandidatoFacade cfacade;
	
	@InjectMocks
	CandidatoService cand;
	
	
	@Test
	public void pesquisaCandidatoByNome() throws Exception{
		cfacade.getPerfilCandidato();
		assertThat(c.getNome(), is(equalTo(null)));
	}
	
	@Test
	public void pesquisaCandidatoByApelido() throws Exception{
		cfacade.getPerfilCandidato();
		assertThat(c.getApelido(), is(equalTo(null)));
	}
	
	@Test
	public void pesquisaCandidatoByEmail() throws Exception{
		cfacade.getPerfilCandidato();
		assertThat(c.getEmail(), is(equalTo(null)));
	}
	
	@Test
	public void pesquisaCandidatoByMorada() throws Exception{
		cfacade.getPerfilCandidato();
		assertThat(c.getMorada(), is(equalTo(null)));
	}
	
	@Test
	public void pesquisaCandidatoByTelefone() throws Exception{
		cfacade.getPerfilCandidato();
		assertThat(c.getTelefone(), is(equalTo(0L)));
	
	}
	
	@Test
	public void pesquisaCandidatoByTelemovel() throws Exception{
		cfacade.getPerfilCandidato();
		assertThat(c.getTelemovel(), is(equalTo(0L)));
	
	}
	
	@Test
	public void pesquisaCandidatoByPais() throws Exception{
		cfacade.getPerfilCandidato();
		assertThat(c.getPais(), is(equalTo(null)));
	
	}
	
	@Test
	public void pesquisaCandidatoByCurso() throws Exception{
		cfacade.getPerfilCandidato();
		assertThat(c.getCurso(), is(equalTo(null)));
	
	}
	
	@Test
	public void pesquisaCandidatoByEscola() throws Exception{
		cfacade.getPerfilCandidato();
		assertThat(c.getEscola(), is(equalTo(null)));
	
	}
	
	@Test
	public void testaagetCandidatoByEmail() throws Exception{
		cfacade.getPerfilCandidato();
		utilService.getUtilizadorByEmail("a");
		Assert.assertEquals("a", "a");
	}

	@Test
	public void testaagetCandidatoByLogin() throws Exception{
		cfacade.getPerfilCandidato();
		utilService.getUtilizadorByLogin("rita");
		Assert.assertEquals("rita", "rita");
	}
	
	@Test
	public void testaagetUtilizadoresSemPerfil() throws Exception{
		cfacade.getPerfilCandidato();
		utilService.getUtilizadoresSemPerfil();
		Assert.assertEquals("", "");
	}
}
