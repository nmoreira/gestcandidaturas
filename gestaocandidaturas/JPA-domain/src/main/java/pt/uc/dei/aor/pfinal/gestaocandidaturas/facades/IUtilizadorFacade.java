package pt.uc.dei.aor.pfinal.gestaocandidaturas.facades;

import java.util.List;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Utilizador;

public interface IUtilizadorFacade extends IEntityFacade<Utilizador> {

	public abstract Utilizador findByLogin(String login);

	public abstract Utilizador findByEmail(String email);

	public abstract List<Utilizador> findByCargo(String cargo);

	public abstract boolean changePassword(long userId, String newPassword);

	public abstract List<Utilizador> getUtilizadoresSemPerfil();

	public abstract boolean passwordMatch(long userId, String password);
}
