package pt.uc.dei.aor.pfinal.gestaocandidaturas.facades;

import java.util.List;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidato;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Perfil;

public interface ICandidatoFacade extends IEntityFacade<Candidato> {

	public abstract Candidato findByLogin(String login);

	public abstract Candidato findByEmail(String email);

	public abstract Candidato createBypassingPassword(Candidato candidato);

	public abstract Perfil getPerfilCandidato();

	public abstract void changePassword(long userId, String newPassword);

	public abstract List<String> getCartasFromCandidatoId(long id);

	public abstract boolean addCartaMotivacao(long candidatoId, String cartaUrl);

}
