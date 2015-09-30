package pt.uc.dei.aor.pfinal.gestaocandidaturas.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.CandidaturaService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidatura;

@Named
@ViewScoped
public class RelatorioTempoMedioContratado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CandidaturaService candidaturaServ;

	private List<Candidatura> candidaturas;
	private String tempoMedio = "Sem contratações";

	@PostConstruct
	private void init() {

		candidaturas = candidaturaServ.getCandidaturasContratadas();

		if (candidaturas.size() > 0) {
			long soma = 0;

			for (Candidatura candidatura : candidaturas) {
				soma += candidatura.getEntrevistas().get(0).getDataEntrevista()
						.getTime()
						- candidatura.getDataCandidatura().getTime();
			}

			long media = soma / candidaturas.size();
			setTempoMedio(dateDifToString(media));
		}
	}

	public String dateDifference(Date d1, Date d2) {

		// in milliseconds
		long diff = d2.getTime() - d1.getTime();

		return dateDifToString(diff);
	}

	public String dateDifToString(long time) {
		String out = "";

		long diffSeconds = time / 1000 % 60;
		long diffMinutes = time / (60 * 1000) % 60;
		long diffHours = time / (60 * 60 * 1000) % 24;
		long diffDays = time / (24 * 60 * 60 * 1000);

		if (diffDays > 0) {
			out = out + diffDays + " dias, ";
		}
		if (diffHours > 0) {
			out = out + diffHours + " horas, ";
		}
		if (diffMinutes > 0) {
			out = out + diffMinutes + " minutos, ";
		}
		if (diffSeconds > 0) {
			out = out + diffSeconds + " segundos.";
		}

		return out;
	}

	public String getTempoMedio() {
		return tempoMedio;
	}

	public void setTempoMedio(String tempoMedio) {
		this.tempoMedio = tempoMedio;
	}

	public List<Candidatura> getCandidaturas() {
		return candidaturas;
	}

	public void setCandidaturas(List<Candidatura> candidaturas) {
		this.candidaturas = candidaturas;
	}

}
