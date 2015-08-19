package pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities;

public enum EstadoPosicao {
	ABERTA("Aberta"), CANDIDATURAS_FECHADAS("Candidaturas fechadas"), ENTREVISTAS(
			"Em entrevistas"), FECHADA("Fechada");

	private String valor;

	private EstadoPosicao(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return this.valor;
	}
}
