package pt.uc.dei.aor.pfinal.gestaocandidaturas.facades;

import java.util.List;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Posicao;

public interface IPosicaoFacade extends IEntityFacade<Posicao> {

	public abstract List<Posicao> findByTitulo(String titulo);

	public abstract List<Posicao> findByEmpresa(String empresa);

	public abstract Posicao findByCodPosicao(String codPosicao);

	public abstract List<Posicao> getPosicoesEmAberto();

}
