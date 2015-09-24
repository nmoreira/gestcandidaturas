package pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.TipoPergunta;

/**
 * Entity implementation class for Entity: TextoLivre
 *
 */
@Entity
@Table(name = "textolivre")
@NamedQueries({
		@NamedQuery(name = "TextoLivre.findAll", query = "SELECT q FROM TextoLivre q"),
		@NamedQuery(name = "TextoLivre.findById", query = "SELECT q FROM TextoLivre q WHERE q.id = :id") })
public class TextoLivre extends Questao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(columnDefinition = "text")
	private String resposta;

	public TextoLivre() {
		super();
		this.setTipo(TipoPergunta.LIVRE);
	}

	public TextoLivre(TextoLivre pergunta) {
		this.setTipo(TipoPergunta.LIVRE);
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
		return new TextoLivre((TextoLivre) pergunta);
	}

}
