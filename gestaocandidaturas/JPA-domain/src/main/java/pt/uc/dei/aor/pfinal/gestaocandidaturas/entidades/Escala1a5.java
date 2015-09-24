package pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.TipoPergunta;

/**
 * Entity implementation class for Entity: Escala1a5
 *
 */
@Entity
@Table(name = "escala1a5")
@NamedQueries({
		@NamedQuery(name = "Escala1a5.findAll", query = "SELECT q FROM Escala1a5 q"),
		@NamedQuery(name = "Escala1a5.findById", query = "SELECT q FROM Escala1a5 q WHERE q.id = :id") })
public class Escala1a5 extends Questao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column
	private int resposta;

	public Escala1a5() {
		super();
		this.setTipo(TipoPergunta.ESCALA_1_5);
	}

	public Escala1a5(Escala1a5 pergunta) {
		this.setTipo(TipoPergunta.ESCALA_1_5);
		this.setOrdem(pergunta.getOrdem());
		this.setPergunta(pergunta.getPergunta());
	}

	@Override
	public String getResposta() {
		return String.valueOf(resposta);
	}

	@Override
	public void setResposta(String resposta) {
		this.resposta = Integer.valueOf(resposta);
	}

	@Override
	public Questao copiaPergunta(Questao pergunta) {
		return new Escala1a5((Escala1a5) pergunta);
	}

}
