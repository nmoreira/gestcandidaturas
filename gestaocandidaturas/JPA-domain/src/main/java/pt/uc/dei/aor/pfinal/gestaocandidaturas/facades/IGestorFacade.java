package pt.uc.dei.aor.pfinal.gestaocandidaturas.facades;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Gestor;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Perfil;

public interface IGestorFacade extends IEntityFacade<Gestor> {

	public abstract Gestor findByLogin(String login);

	public abstract Gestor findByEmail(String email);

	public abstract Gestor createBypassingPassword(Gestor gestor);

	public abstract Perfil getPerfilGestor();

	public abstract void changePassword(long userId, String newPassword);

}
