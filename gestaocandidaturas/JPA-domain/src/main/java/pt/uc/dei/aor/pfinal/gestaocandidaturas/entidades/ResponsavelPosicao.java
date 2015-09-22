package pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
		@NamedQuery(name = "ResponsavelPosicao.findAll", query = "SELECT r FROM ResponsavelPosicao r"),
		@NamedQuery(name = "ResponsavelPosicao.findById", query = "SELECT r FROM ResponsavelPosicao r WHERE r.id = :id") })
public abstract class ResponsavelPosicao extends GrupoEntrevistadores {

	@OneToMany(mappedBy = "responsavel")
	private List<Posicao> listaPosicoes;

	public List<Posicao> getListaPosicoes() {
		return listaPosicoes;
	}

	public void setListaPosicoes(List<Posicao> listaPosicoes) {
		this.listaPosicoes = listaPosicoes;
	}

	public ResponsavelPosicao() {
		// TODO Auto-generated constructor stub
	}

	public ResponsavelPosicao(String login, String password, String nome,
			String apelido, String email) {
		super(login, password, nome, apelido, email);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (this.getId() ^ (this.getId() >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResponsavelPosicao other = (ResponsavelPosicao) obj;
		if (this.getId() != other.getId())
			return false;
		return true;
	}
}
