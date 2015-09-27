package pt.uc.dei.aor.pfinal.gestaocandidaturas.view;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.EntrevistaService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Entrevista;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.mail.CommonsMail;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.DisplayMessages;

@Named
@ViewScoped
public class MarcarEntrevista implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private EntrevistaService entrevistaServ;

	@Inject
	private CurrentSession current;

	@Inject
	private CommonsMail mail;

	private List<Entrevista> minhasEntrevistas;

	@PostConstruct
	private void init() {
		minhasEntrevistas = entrevistaServ.getEntrevistaByEntrevistador(current
				.getCurrentUser().getId());
	}

	public void marcarData(Entrevista ent) {
		if (entrevistaServ.atualizarEntrevista(ent)) {
			DisplayMessages.addInfoMessage("Entrevista marcada com sucesso");
			String data = new SimpleDateFormat("dd-MM-yyyy 'às' HH:mm")
					.format(ent.getDataEntrevista());
			mail.enviaEmailSimples("Entrevista agendada",
					"Foi agendada uma entrevista para a posição a que se candidatou.\n"
							+ "Posição: "
							+ ent.getCandidatura().getPosicao().getTitulo()
							+ "\n" + "Data da entrevista: " + data, ent
							.getCandidatura().getCandidato());
		} else {
			DisplayMessages
					.addErrorMessage("Erro ao marcar a data da entrevista");
		}
	}

	public List<Entrevista> getMinhasEntrevistas() {
		return minhasEntrevistas;
	}

	public void setMinhasEntrevistas(List<Entrevista> minhasEntrevistas) {
		this.minhasEntrevistas = minhasEntrevistas;
	}

	public Date getDataAtual() {
		return new Date();
	}
}
