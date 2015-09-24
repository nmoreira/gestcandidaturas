package pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.TipoPergunta;

/**
 * Entity implementation class for Entity: SimNao
 *
 */
@Entity
@Table(name = "simnao")
@NamedQueries({
		@NamedQuery(name = "SimNao.findAll", query = "SELECT q FROM SimNao q"),
		@NamedQuery(name = "SimNao.findById", query = "SELECT q FROM SimNao q WHERE q.id = :id") })
public class SimNao extends Questao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column
	private String resposta;

	public SimNao() {
		super();
		this.setTipo(TipoPergunta.SIM_NAO);
	}

	public SimNao(SimNao pergunta) {
		this.setTipo(TipoPergunta.SIM_NAO);
		this.setOrdem(pergunta.getOrdem());
		this.setPergunta(pergunta.getPergunta());
	}

	@Override
	public String getResposta() {
		return resposta;
	}

	@Override
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	@Override
	public Questao copiaPergunta(Questao pergunta) {
		return new SimNao((SimNao) pergunta);
	}

}
