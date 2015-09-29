package pt.uc.dei.aor.pfinal.gestaocandidaturas.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.EntrevistaService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Entrevista;

@Named
@ViewScoped
public class RelatorioResultadosEntrevistasPeriodo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date dataInicio;
	private Date dataFim;

	private List<Entrevista> resultados;

	@Inject
	private EntrevistaService entrevistaServ;

	public void pesquisar() {
		setResultados(entrevistaServ.getEntrevistasEntreDatas(dataInicio,
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

	public List<Entrevista> getResultados() {
		return resultados;
	}

	public void setResultados(List<Entrevista> resultados) {
		this.resultados = resultados;
	}
}
