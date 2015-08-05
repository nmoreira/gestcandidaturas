package pt.uc.dei.aor.pfinal.gestaocandidaturas.facades;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Perfil;

public interface IPerfilFacade extends IEntityFacade<Perfil> {

	public abstract Perfil findByName(String nomePerfil);

}
