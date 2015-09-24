package pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Feedback;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.FeedbackFacade;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.facades.IFeedbackFacade;

/**
 * Session Bean implementation class FeedbackService
 */
@Stateless
@LocalBean
public class FeedbackService {

	@EJB(beanInterface = FeedbackFacade.class)
	private IFeedbackFacade feedbackFacade;

	/**
	 * Default constructor.
	 */
	public FeedbackService() {
		// TODO Auto-generated constructor stub
	}

	public void createNewFeedback(Feedback feedback) {
		feedbackFacade.create(feedback);
	}

	public Feedback getFeedBackById(long id) {
		return feedbackFacade.find(id);
	}

	public List<Feedback> getAllFeedBack() {
		return (List<Feedback>) feedbackFacade.findAll();
	}

	public boolean atualizarFeedback(Feedback feedback) {
		if (feedbackFacade.update(feedback) != null) {
			return true;
		} else {
			return false;
		}
	}
}
