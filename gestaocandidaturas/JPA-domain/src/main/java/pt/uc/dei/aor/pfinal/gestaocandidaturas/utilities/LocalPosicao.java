package pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities;

public enum LocalPosicao {
	COIMBRA("Coimbra"), PORTO("Porto"), LISBOA("Lisboa"), CLIENTE("Cliente");

	private String valor;

	private LocalPosicao(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return this.valor;
	}

}
