package pt.uc.dei.aor.pfinal.gestaocandidaturas;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.CandidaturaService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidato;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidatura;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.view.PesquisaCandidaturas;


@RunWith(MockitoJUnitRunner.class)
public class PesquisaCandidaturasTest {
	
	@Mock
	CandidaturaService candServ;
	
	@Mock
	Candidato c;
	
	@Mock
	Candidatura cand;
	
	@InjectMocks
	PesquisaCandidaturas candidaturas;
	
	@Test
	public void testPesquisaCandidatoApelido() throws Exception{
		 Candidatura cand1 = new Candidatura();
		 Candidato c = new Candidato();
		
		c.setApelido("Costa");
		
		 List<Candidatura> pesquisa = candServ.pesquisaCandidaturasByCandidatoApelido("Costa");
		 pesquisa.add(cand1);
		  
		 assertThat(c.getApelido(), is(equalTo("Costa"))); 
		 assertEquals("deveria trazer a lista de tamanho correto", pesquisa.size(), 1);
		 
		 
		 assertNotNull("nao deveria ser nula", pesquisa);
	
	}
	
	@Test
	public void testPesquisaCandidatoCidade() throws Exception{
		 Candidatura cand1 = new Candidatura();
		 Candidato c = new Candidato();
		
		c.setCidade("Coimbra");
				 
		 List<Candidatura> pesquisa = candServ.pesquisaCandidaturasByCandidatoCidade("Coimbra");
		 pesquisa.add(cand1);
		 
		 assertThat(c.getCidade(), is(equalTo("Coimbra"))); 
		
		 assertEquals("deveria trazer a lista de tamanho correto", pesquisa.size(), 1);
		 
		 assertNotNull("nao deveria ser nula", pesquisa);
	
	}
	
	@Test
	public void testPesquisaCandidato() throws Exception{
		 Candidatura cand1 = new Candidatura();
		 Candidato c = new Candidato();
		
		c.setEmail("a@aa");
		
		 List<Candidatura> pesquisa = candServ.pesquisaCandidaturasByCandidatoEmail("a@aa");
		 pesquisa.add(cand1);
		  
		 assertThat(c.getEmail(), is(equalTo("a@aa"))); 
		 assertEquals("deveria trazer a lista de tamanho correto", pesquisa.size(), 1);
		 
		 
		 assertNotNull("nao deveria ser nula", pesquisa);
	
	}
}
