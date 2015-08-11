package pt.uc.dei.aor.pfinal.gestaocandidaturas.facades;

import java.util.List;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.ResponsavelPosicao;

public interface IResponsavelPosicaoFacade {

	public abstract List<ResponsavelPosicao> findAll();

	public abstract ResponsavelPosicao findById(long id);
}
