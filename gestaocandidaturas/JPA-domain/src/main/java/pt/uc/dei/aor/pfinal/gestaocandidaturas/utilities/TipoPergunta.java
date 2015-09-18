package pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities;

public enum TipoPergunta {
	LIVRE("Resposta Livre"), SIM_NAO("Sim ou Não"), ESCALA_1_5(
			"Escala de 1 a 5");

	private String valor;

	private TipoPergunta(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return this.valor;
	}
}
