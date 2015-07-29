package pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ResponsavelPosicao extends Utilizador {

	@OneToMany(mappedBy = "responsavel")
	private List<Posicao> listaPosicoes;

	public List<Posicao> getListaPosicoes() {
		return listaPosicoes;
	}

	public void setListaPosicoes(List<Posicao> listaPosicoes) {
		this.listaPosicoes = listaPosicoes;
	}

}
