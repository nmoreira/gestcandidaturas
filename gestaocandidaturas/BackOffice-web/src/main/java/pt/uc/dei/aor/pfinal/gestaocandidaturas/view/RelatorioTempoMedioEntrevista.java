package pt.uc.dei.aor.pfinal.gestaocandidaturas.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.EntrevistaService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Entrevista;

@Named
@ViewScoped
public class RelatorioTempoMedioEntrevista implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private EntrevistaService entrevistaServ;

	private List<Entrevista> entrevistas;
	private String tempoMedio = "Sem entrevistas";

	@PostConstruct
	private void init() {
		entrevistas = entrevistaServ.getEntrevistasComData();
		if (entrevistas.size() > 0) {
			long soma = 0;

			for (Entrevista entrevista : entrevistas) {
				soma += entrevista.getDataEntrevista().getTime()
						- entrevista.getCandidatura().getDataCandidatura()
								.getTime();
			}

			long media = soma / entrevistas.size();
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

	public List<Entrevista> getEntrevistas() {
		return entrevistas;
	}

	public void setEntrevistas(List<Entrevista> entrevistas) {
		this.entrevistas = entrevistas;
	}

}
