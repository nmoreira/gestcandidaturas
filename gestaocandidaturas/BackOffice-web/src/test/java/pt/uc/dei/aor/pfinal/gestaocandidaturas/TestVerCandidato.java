package pt.uc.dei.aor.pfinal.gestaocandidaturas;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.CandidatoService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.CandidaturaService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidato;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidatura;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.view.VerCandidato;

@RunWith(MockitoJUnitRunner.class)
public class TestVerCandidato {
	
	@Mock
	CandidatoService candidatoServ;

	@Mock
	CandidaturaService candidaturaServ;
	
	@InjectMocks
	VerCandidato ver;


	@Test
	public void testGetCandidato() throws Exception{
		Candidato cand = new Candidato();
		cand.setId(1);
		
		candidatoServ.getCandidatoById(1);
		
		assertThat(ver.getCandidato(), is(equalTo(null))); 
	}
	
	@Test
	public void testAtualizaCandidato() throws Exception{
		
		Date inicio = null;
		Date fim = null;
		Candidatura cand1 = new Candidatura();
		 List<Candidatura> pesquisa = candidaturaServ.getCandidaturasEntreDatas(inicio, fim);
		 pesquisa.add(cand1);
		  
		assertEquals (ver.getCandidaturas(),null);
		 assertEquals("deveria trazer a lista de tamanho correto", pesquisa.size(), 1);
		 
	}
	
}
