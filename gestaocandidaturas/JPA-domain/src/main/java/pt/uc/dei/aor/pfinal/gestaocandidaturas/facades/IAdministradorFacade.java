package pt.uc.dei.aor.pfinal.gestaocandidaturas.facades;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Administrador;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Perfil;

public interface IAdministradorFacade extends IEntityFacade<Administrador> {

	public abstract Administrador findByLogin(String login);

	public abstract Administrador findByEmail(String email);

	public abstract Administrador createBypassingPassword(Administrador newAdmin);

	public abstract Perfil getPerfilAdmin();

	public abstract void changePassword(long userId, String newPassword);

}
