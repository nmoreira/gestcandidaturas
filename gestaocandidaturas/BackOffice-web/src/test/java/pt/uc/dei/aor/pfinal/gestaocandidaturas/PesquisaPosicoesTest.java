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

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.PosicaoService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Posicao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.view.PesquisaPosicoes;


@RunWith(MockitoJUnitRunner.class)
public class PesquisaPosicoesTest {
	
	@Mock
	PosicaoService posicaoServ;
	
	@Mock
	Posicao pos;
	
	@InjectMocks
	PesquisaPosicoes pesquisa;
	
	@Test
	public void testPesquisaPosicao() throws Exception{
		 Posicao pos1 = new Posicao();
		
		 pos1.setTitulo("aaa");
		 
		 List<Posicao> pesquisa = posicaoServ.pesquisaTitulo("aaa");
		 pesquisa.add(pos1);
		 
		 assertThat(pos1.getTitulo(), is(equalTo("aaa"))); 
		 assertEquals("deveria estar trazendo a lista de tamanho correto", pesquisa.size(), 1);
		 
		 
		 assertNotNull("nao deveria ser nula", pesquisa);
		 
		 assertEquals("deveria estar trazendo a lista correta", pesquisa.get(0).getTitulo(), "aaa");
		 assertEquals("deveria estar trazendo a lista correta", pesquisa.get(0).getEstado(), null);
	}

}
