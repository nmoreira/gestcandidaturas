package pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities;

import org.junit.Before;
import org.junit.Test;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.CandidatoService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidato;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.ICandidatoFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.view.NovoCandidato;


public class NovoCandidatoTest {
	
	private NovoCandidato novoCandidato = new NovoCandidato();
	private CandidatoService candidato = new CandidatoService();
	
	private ICandidatoFacade candidatoFacade;
	@Before
	public void setup(){
		novoCandidato = new NovoCandidato();
		candidato = new CandidatoService();
	}
	
	@Test
	public void deveCriarNovoCandidato() throws Exception{
		String login = "Rita";
		String password = "123";
	    String nome = "Rita";
		String apelido = "Costa";
		String email = "rita.c@portugalmail.pt";
	    String morada = "Rua da Esperança";
		String cidade ="Coimbra";
	    long telefone = 231495636;
		long telemovel = 916489593;
		String pais = "Portugal";
		String curso = "Engenharia Informatica";
		String escola = "Universidade de Coimbra";
		String cv ="cv";
		String idLinkedin = "rita";
		
		Candidato newCandidato = new Candidato(login,password,nome,apelido,email,morada,cidade,telefone,telemovel,pais,curso,escola,cv,idLinkedin);
		newCandidato.setPerfil(candidatoFacade.getPerfilCandidato());
        candidato.createNewCandidato(newCandidato);
		
		
	}
	
	
	@Test 
	public void getNome() throws Exception{
		 novoCandidato.setNome("Maria");
		 
	}
	
	
	
}
