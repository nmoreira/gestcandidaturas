package pt.uc.dei.aor.pfinal.gestaocandidaturas.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.CandidaturaService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidatura;

@Named
@ViewScoped
public class RelatorioCandidatosPeriodo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date dataInicio;
	private Date dataFim;

	private List<Candidatura> resultados;

	@Inject
	private CandidaturaService candidaturaServ;

	public void pesquisar() {
		setResultados(candidaturaServ.getCandidaturasEntreDatas(dataInicio,
				dataFim));
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public List<Candidatura> getResultados() {
		return resultados;
	}

	public void setResultados(List<Candidatura> resultados) {
		this.resultados = resultados;
	}

}
