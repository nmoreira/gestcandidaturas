package pt.uc.dei.aor.pfinal.gestaocandidaturas.facades;

import java.util.Date;
import java.util.List;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Posicao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.AreaTecnica;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.EstadoPosicao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.LocalPosicao;

public interface IPosicaoFacade extends IEntityFacade<Posicao> {

	public abstract List<Posicao> findByTitulo(String titulo);

	public abstract List<Posicao> findByEmpresa(String empresa);

	public abstract Posicao findByCodPosicao(String codPosicao);

	public abstract List<Posicao> getPosicoesEmAberto();

	public abstract List<Posicao> searchByDataAbertura(Date data);

	public abstract List<Posicao> searchByCodPosicao(String codPosicao);

	public abstract List<Posicao> searchByTitulo(String titulo);

	public abstract List<Posicao> searchByLocal(LocalPosicao local);

	public abstract List<Posicao> searchByEstado(EstadoPosicao estado);

	public abstract List<Posicao> searchByEmpresa(String empresa);

	public abstract List<Posicao> searchByAreaTecnica(AreaTecnica area);

}
