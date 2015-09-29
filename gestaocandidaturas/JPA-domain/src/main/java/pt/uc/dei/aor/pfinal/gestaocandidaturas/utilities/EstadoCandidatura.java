package pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities;

public enum EstadoCandidatura {
	SUBMETIDA("Candidatura Submetida"), ESPONTANEA_SUBMETIDA(
			"Candidatura Espontânea Submetida"), POSICAO_ATRIBUIDA(
			"Candidatura submetida, com posição atribuida"), ENTREVISTA_AGENDADA(
			"Entrevista agendada"), EM_ANALISE("Candidatura em análise"), CONTRATADO(
			"Contratado"), REJEITADO("Candidatura rejeitada");

	private String valor;

	private EstadoCandidatura(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return this.valor;
	}
}
