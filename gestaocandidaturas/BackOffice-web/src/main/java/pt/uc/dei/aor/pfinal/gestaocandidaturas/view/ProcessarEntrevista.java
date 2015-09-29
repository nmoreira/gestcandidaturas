package pt.uc.dei.aor.pfinal.gestaocandidaturas.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.CandidaturaService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.EntrevistaService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.Escala1a5Service;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.FeedbackService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.PosicaoService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.SimNaoService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.TextoLivreService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Entrevista;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Escala1a5;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Feedback;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Questao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.SimNao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.TextoLivre;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.DisplayMessages;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.EstadoCandidatura;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.LocalPosicao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.TipoPergunta;

@Named
@ViewScoped
public class ProcessarEntrevista implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private EntrevistaService entrevistaServ;

	@Inject
	private PosicaoService posicaoServ;

	@Inject
	private TextoLivreService textolivreServ;

	@Inject
	private Escala1a5Service escala1a5Serv;

	@Inject
	private SimNaoService simNaoServ;

	@Inject
	private FeedbackService feedbackServ;

	@Inject
	private CandidaturaService candidaturaServ;

	private Entrevista entrevista;

	@PostConstruct
	private void init() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		long entid = Long.parseLong(params.get("entid"));

		entrevista = entrevistaServ.getEntrevistaByIdFetchQuestoes(entid);

		long posicaoId = entrevista.getCandidatura().getPosicao().getId();
		List<LocalPosicao> locais = posicaoServ.getLocaisPosicao(posicaoId);
		entrevista.getCandidatura().getPosicao().setLocal(locais);

		if (entrevista.getFeedback() == null) {
			Feedback feedback = new Feedback();
			feedback.setEntrevista(entrevista);
			feedbackServ.createNewFeedback(feedback);
			entrevista.setFeedback(feedback);
			entrevistaServ.atualizarEntrevista(entrevista);
		}
	}

	public Entrevista getEntrevista() {
		return entrevista;
	}

	public void setEntrevista(Entrevista entrevista) {
		this.entrevista = entrevista;
	}

	public void guardarEntrevista() {
		if (guardarRespostas() && guardarFeedback()) {
			entrevista.setDataEntrevista(new Date());
			candidaturaServ.atualizarCandidatura(entrevista.getCandidatura());
			if (entrevistaServ.atualizarEntrevista(entrevista)) {
				DisplayMessages
						.addInfoMessage("Entrevista guardada com sucesso");
			} else {
				DisplayMessages.addErrorMessage("Erro ao guardar a entrevista");
			}
		} else {
			DisplayMessages.addErrorMessage("Erro ao guardar a entrevista");
		}
	}

	public boolean guardarRespostas() {
		boolean erro = false;
		for (Questao questao : entrevista.getQuestoesEntrevista()) {
			if (questao.getTipo().equals(TipoPergunta.ESCALA_1_5)) {
				if (escala1a5Serv
						.atualizarQuestaoEscala1a5((Escala1a5) questao) == null) {
					DisplayMessages
							.addErrorMessage("Erro ao guardar a resposta da pergunta nº "
									+ questao.getOrdem());
					erro = true;
				}
			} else if (questao.getTipo().equals(TipoPergunta.LIVRE)) {
				if (textolivreServ
						.atualizarQuestaoTextoLivre((TextoLivre) questao) == null) {
					DisplayMessages
							.addErrorMessage("Erro ao guardar a resposta da pergunta nº "
									+ questao.getOrdem());
					erro = true;
				}
			} else if (questao.getTipo().equals(TipoPergunta.SIM_NAO)) {
				if (simNaoServ.atualizarQuestaoSimNao((SimNao) questao) == null) {
					DisplayMessages
							.addErrorMessage("Erro ao guardar a resposta da pergunta nº "
									+ questao.getOrdem());
					erro = true;
				}
			}
		}
		return !erro;
	}

	public boolean guardarFeedback() {
		Feedback f = entrevista.getFeedback();
		if (!feedbackServ.atualizarFeedback(f)) {
			DisplayMessages
					.addErrorMessage("Erro ao guardar o Feedback da entrevista");
			return false;
		} else {
			return true;
		}
	}

	public EstadoCandidatura[] getEstadosCandidatura() {
		return EstadoCandidatura.values();
	}
}
