package pt.uc.dei.aor.pfinal.gestaocandidaturas.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.EntrevistaService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Entrevista;

@Named
@ViewScoped
public class ListaEntrevistasUtilizador implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CurrentSession current;
	
	@Inject
	private EntrevistaService entrevistaServ;
	
	private List<Entrevista> minhasEntrevistas;
	
	@PostConstruct
	private void init(){
		minhasEntrevistas = entrevistaServ.getEntrevistaByEntrevistador(current.getCurrentUser().getId());
	}

	public List<Entrevista> getMinhasEntrevistas() {
		return minhasEntrevistas;
	}

	public void setMinhasEntrevistas(List<Entrevista> minhasEntrevistas) {
		this.minhasEntrevistas = minhasEntrevistas;
	}

}
