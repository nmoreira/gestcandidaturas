package pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.TextoLivre;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.ITextoLivreFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.TextoLivreFacade;

/**
 * Session Bean implementation class TextoLivreService
 */
@Stateless
@LocalBean
public class TextoLivreService {

	@EJB(beanInterface = TextoLivreFacade.class)
	private ITextoLivreFacade textoLivreFacade;

	/**
	 * Default constructor.
	 */
	public TextoLivreService() {
		// TODO Auto-generated constructor stub
	}

	public boolean createNewQuestaoTextoLivre(TextoLivre newTextoLivre) {
		if (textoLivreFacade.create(newTextoLivre) != null) {
			return true;
		} else {
			return false;
		}
	}

	public TextoLivre getQuestaoTextoLivreById(long id) {
		return textoLivreFacade.find(id);
	}

	public TextoLivre atualizarQuestaoTextoLivre(TextoLivre questaoTextoLivre) {
		return textoLivreFacade.update(questaoTextoLivre);
	}

	public boolean apagaQuestaoTextoLivre(TextoLivre questaoTextoLivre) {
		return textoLivreFacade.delete(questaoTextoLivre);
	}

}
