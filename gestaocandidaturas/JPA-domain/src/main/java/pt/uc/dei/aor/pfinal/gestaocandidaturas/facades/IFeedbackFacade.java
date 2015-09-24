package pt.uc.dei.aor.pfinal.gestaocandidaturas.facades;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Entrevista;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Feedback;

public interface IFeedbackFacade extends IEntityFacade<Feedback> {

	public abstract Feedback getFeedBackByEntrevista(Entrevista entrevista);
}
