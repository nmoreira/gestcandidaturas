package pt.uc.dei.aor.pfinal.gestaocandidaturas.facades;

import java.util.Date;
import java.util.List;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Entrevista;

public interface IEntrevistaFacade extends IEntityFacade<Entrevista> {

	public abstract List<Entrevista> findByCandidatura(long candidaturaId);

	public abstract List<Entrevista> findByEntrevistador(long entrevistadorId);

	public abstract List<Entrevista> findByData(Date dataEntrevista);

	public abstract Entrevista findByIdFetchQuestoes(long entrevistaId);

	public abstract List<Entrevista> getEntrevistasEntreDatas(Date inicio,
			Date fim);

	public abstract List<Entrevista> getEntrevistasComData();
}
