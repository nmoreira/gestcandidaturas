package pt.uc.dei.aor.pfinal.gestaocandidaturas.facades;

import java.util.List;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Guiao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Questao;

public interface IGuiaoFacade extends IEntityFacade<Guiao> {

	public abstract List<Questao> getQuestoesByGuiaoId(long id);

	public abstract List<Guiao> getGuioesDisponiveis();

	public abstract List<Guiao> getGuioesDisponiveisFetchQuestoes();

	public abstract boolean guiaoEmUso(long guiaoId);

	public abstract Guiao getGuiaoByIdFetchQuestoes(long guiaoId);

}
