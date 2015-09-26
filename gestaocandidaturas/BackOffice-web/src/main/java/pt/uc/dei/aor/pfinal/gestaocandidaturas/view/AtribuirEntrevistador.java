package pt.uc.dei.aor.pfinal.gestaocandidaturas.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.CandidaturaService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.EntrevistaService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.GrupoEntrevistadoresService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidatura;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Entrevista;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.GrupoEntrevistadores;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.mail.CommonsMail;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.DisplayMessages;

@Named
@ViewScoped
public class AtribuirEntrevistador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CandidaturaService candidaturaServ;

	@Inject
	private GrupoEntrevistadoresService entrevistadoresServ;

	@Inject
	private EntrevistaService entrevistaServ;

	@Inject
	private CommonsMail mail;

	private List<Candidatura> candidaturasSemEntrevista;
	private List<GrupoEntrevistadores> entrevistadores;

	private GrupoEntrevistadores entrevistador;
	private Candidatura candidatura;

	@PostConstruct
	private void init() {
		setCandidaturasSemEntrevista(candidaturaServ
				.getCandidaturasSemEntrevistas());
		setEntrevistadores(entrevistadoresServ.getAllEntrevistadores());
	}

	public void atribuirEntrevistador() {
		Entrevista entrevista = new Entrevista();
		entrevista.setCandidatura(candidatura);
		entrevista.setEntrevistador(entrevistador);
		if (entrevistaServ.createNewEntrevista(entrevista)) {
			DisplayMessages
					.addInfoMessage("Entrevista criada com sucesso, e atribuida a "
							+ entrevistador.getNome()
							+ " "
							+ entrevistador.getApelido());
			candidaturasSemEntrevista.remove(candidatura);

			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();

			String link = context.getRequestScheme() + "://"
					+ context.getRequestServerName() + ":"
					+ context.getRequestServerPort()
					+ context.getRequestContextPath()
					+ "/paginas/gest/vercandidato.xhtml?candidatoid="
					+ candidatura.getCandidato().getId();

			String cv = context.getRequestScheme() + "://"
					+ context.getRequestServerName() + ":"
					+ context.getRequestServerPort() + "/FrontOffice-web"
					+ candidatura.getCandidato().getCv();

			mail.enviaEmailComAnexo(
					"Entrevista atribuida",
					"Foi-lhe atribuida a entrevista de uma candidarura.\n"
							+ "Posição: "
							+ candidatura.getPosicao().getTitulo()
							+ "\n"
							+ "Candidato: "
							+ candidatura.getCandidato().getNome()
							+ " "
							+ candidatura.getCandidato().getApelido()
							+ "\n\n"
							+ "Ver candidato em: "
							+ link
							+ "\n\n"
							+ "Deve aceder à aplicação e marcar a data da entrevista!",
					entrevistador, cv);
		} else {
			DisplayMessages.addErrorMessage("Erro ao criar a entrevista");
		}
	}

	public List<Candidatura> getCandidaturasSemEntrevista() {
		return candidaturasSemEntrevista;
	}

	public void setCandidaturasSemEntrevista(
			List<Candidatura> candidaturasSemEntrevista) {
		this.candidaturasSemEntrevista = candidaturasSemEntrevista;
	}

	public List<GrupoEntrevistadores> getEntrevistadores() {
		return entrevistadores;
	}

	public void setEntrevistadores(List<GrupoEntrevistadores> entrevistadores) {
		this.entrevistadores = entrevistadores;
	}

	public GrupoEntrevistadores getEntrevistador() {
		return entrevistador;
	}

	public void setEntrevistador(GrupoEntrevistadores entrevistador) {
		this.entrevistador = entrevistador;
	}

	public Candidatura getCandidatura() {
		return candidatura;
	}

	public void defineCandidatura(Candidatura candidatura) {
		this.candidatura = candidatura;
	}
}
