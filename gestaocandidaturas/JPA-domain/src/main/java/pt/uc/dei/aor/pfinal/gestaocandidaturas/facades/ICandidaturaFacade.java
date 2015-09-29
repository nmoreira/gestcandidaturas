package pt.uc.dei.aor.pfinal.gestaocandidaturas.facades;

import java.util.Date;
import java.util.List;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidatura;

public interface ICandidaturaFacade extends IEntityFacade<Candidatura> {

	public abstract List<Candidatura> findByCandidato(long candidatoId);

	public abstract List<Candidatura> findByPosicao(long posicaoId);

	public abstract Candidatura findByCandidatoAndPosicao(long candidatoId,
			long posicaoId);

	public abstract boolean criaCandidaturaEspontanea(Candidatura candidatura);

	public abstract List<Candidatura> getCandidaturasEspontaneas();

	public abstract List<Candidatura> searchCandidaturaByCandidatoNome(
			String nome);

	public abstract List<Candidatura> searchCandidaturaByCandidatoApelido(
			String apelido);

	public abstract List<Candidatura> searchCandidaturaByCandidatoEmail(
			String email);

	public abstract List<Candidatura> searchCandidaturaByCandidatoMorada(
			String morada);

	public abstract List<Candidatura> searchCandidaturaByCandidatoCidade(
			String cidade);

	public abstract List<Candidatura> searchCandidaturaByCandidatoTelefone(
			long telefone);

	public abstract List<Candidatura> searchCandidaturaByCandidatoTelemovel(
			long telemovel);

	public abstract List<Candidatura> searchCandidaturaByCandidatoPais(
			String pais);

	public abstract List<Candidatura> searchCandidaturaByCandidatoCurso(
			String curso);

	public abstract List<Candidatura> searchCandidaturaByCandidatoEscola(
			String escola);

	public abstract List<Candidatura> getCandidaturasSemEntrevistas();

	public abstract List<Candidatura> getCandidaturasEntreDatas(Date inicio,
			Date fim);

	public abstract List<Candidatura> getCandidaturasEspontaneasEntreDatas(
			Date inicio, Date fim);

}
