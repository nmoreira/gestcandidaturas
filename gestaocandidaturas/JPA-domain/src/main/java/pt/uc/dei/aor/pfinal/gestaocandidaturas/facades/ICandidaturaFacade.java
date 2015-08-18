package pt.uc.dei.aor.pfinal.gestaocandidaturas.facades;

import java.util.List;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidatura;

public interface ICandidaturaFacade extends IEntityFacade<Candidatura> {

	public abstract List<Candidatura> findByCandidato(long candidatoId);

	public abstract List<Candidatura> findByPosicao(long posicaoId);

	public abstract Candidatura findByCandidatoAndPosicao(long candidatoId,
			long posicaoId);

}
