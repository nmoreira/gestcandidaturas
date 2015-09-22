package pt.uc.dei.aor.pfinal.gestaocandidaturas.facades;

import java.util.List;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.GrupoEntrevistadores;

public interface IGrupoEntrevistadoresFacade {

	public abstract List<GrupoEntrevistadores> findAll();

	public abstract GrupoEntrevistadores findById(long id);
}
