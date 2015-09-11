package pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Entrevista;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.EntrevistaFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.IEntrevistaFacade;

/**
 * Session Bean implementation class EntrevistaService
 */
@Stateless
@LocalBean
public class EntrevistaService {

	@EJB(beanInterface = EntrevistaFacade.class)
	private IEntrevistaFacade entrevistaFacade;

	/**
	 * Default constructor.
	 */
	public EntrevistaService() {
		// TODO Auto-generated constructor stub
	}

	public void createNewEntrevista(Entrevista newEntrevista) {
		entrevistaFacade.create(newEntrevista);
	}

	public Entrevista getEntrevistaById(long id) {
		return entrevistaFacade.find(id);
	}

	public boolean atualizarEntrevista(Entrevista ent) {
		if (entrevistaFacade.update(ent) != null) {
			return true;
		} else {
			return false;
		}
	}

	public List<Entrevista> getEntrevistaByCandidatura(long candidaturaId) {
		return entrevistaFacade.findByCandidatura(candidaturaId);
	}

	public List<Entrevista> getEntrevistaByEntrevistador(long entrevistadorId) {
		return entrevistaFacade.findByEntrevistador(entrevistadorId);
	}

	public List<Entrevista> getEntrevistaByData(Date data) {
		return entrevistaFacade.findByData(data);
	}

}
