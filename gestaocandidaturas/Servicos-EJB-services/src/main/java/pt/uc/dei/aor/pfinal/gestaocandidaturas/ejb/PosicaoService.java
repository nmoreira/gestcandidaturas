package pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Posicao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.IPosicaoFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.PosicaoFacade;

/**
 * Session Bean implementation class AdministradorService
 */
@Stateless
@LocalBean
public class PosicaoService {

	@EJB(beanInterface = PosicaoFacade.class)
	private IPosicaoFacade posicaoFacade;

	/**
	 * Default constructor.
	 */
	public PosicaoService() {
		// TODO Auto-generated constructor stub
	}

	public void createNewPosicao(Posicao posicao) {
		posicaoFacade.create(posicao);
	}

	public List<Posicao> listaPosicoes() {
		return (List<Posicao>) posicaoFacade.findAll();
	}

	public boolean existeCodPosicao(String codPosicao) {
		if (posicaoFacade.findByCodPosicao(codPosicao) == null)
			return false;
		else
			return true;
	}

	public List<Posicao> getPosicoesEmAberto() {
		return posicaoFacade.getPosicoesEmAberto();
	}

	public Posicao getPosicaoById(long id) {
		return posicaoFacade.find(id);
	}
}
