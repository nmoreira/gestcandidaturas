package pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.ResponsavelPosicao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.IResponsavelPosicaoFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.ResponsavelPosicaoFacade;

/**
 * Session Bean implementation class AdministradorService
 */
@Stateless
@LocalBean
public class ResponsavelPosicaoService {

	@EJB(beanInterface = ResponsavelPosicaoFacade.class)
	private IResponsavelPosicaoFacade respFacade;

	/**
	 * Default constructor.
	 */
	public ResponsavelPosicaoService() {
		// TODO Auto-generated constructor stub
	}

	public List<ResponsavelPosicao> getAllResponsaveisPosicao() {
		return respFacade.findAll();
	}

	public ResponsavelPosicao getResponsavelPosicaoById(long id) {
		return respFacade.findById(id);
	}

	public ResponsavelPosicao getResponsavelPosicaoById(String id) {
		final long longid = Long.parseLong(id);
		return getResponsavelPosicaoById(longid);
	}

}
