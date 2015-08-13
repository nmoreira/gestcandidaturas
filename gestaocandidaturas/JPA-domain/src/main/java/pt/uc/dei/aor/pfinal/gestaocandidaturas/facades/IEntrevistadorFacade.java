package pt.uc.dei.aor.pfinal.gestaocandidaturas.facades;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Entrevistador;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Perfil;

public interface IEntrevistadorFacade extends IEntityFacade<Entrevistador> {

	public abstract Entrevistador findByLogin(String login);

	public abstract Entrevistador findByEmail(String email);

	public abstract Entrevistador createBypassingPassword(
			Entrevistador entrevistador);

	public abstract Perfil getPerfilEntrevistador();

	public abstract void changePassword(long userId, String newPassword);

}
