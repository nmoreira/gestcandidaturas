package pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Posicao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.IPosicaoFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.PosicaoFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.AreaTecnica;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.EstadoPosicao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.LocalPosicao;

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

	public List<Posicao> getPosicoesByData(Date data) {
		return posicaoFacade.searchByDataAbertura(data);
	}

	public List<Posicao> pesquisaCodPosicao(String cod) {
		return posicaoFacade.searchByCodPosicao(cod);
	}

	public List<Posicao> pesquisaTitulo(String titulo) {
		return posicaoFacade.searchByTitulo(titulo);
	}

	public List<Posicao> pesquisaLocal(LocalPosicao local) {
		return posicaoFacade.searchByLocal(local);
	}

	public List<Posicao> pesquisaEstado(EstadoPosicao estado) {
		return posicaoFacade.searchByEstado(estado);
	}

	public List<Posicao> pesquisaEmpresa(String empresa) {
		return posicaoFacade.searchByEmpresa(empresa);
	}

	public List<Posicao> pesquisaArea(AreaTecnica area) {
		return posicaoFacade.searchByAreaTecnica(area);
	}
}
