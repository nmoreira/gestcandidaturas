package pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities;

public enum AreaTecnica {
	SSPA("SSPA – Software and Security Product Assurance"), Dot_Net_Development(
			".Net Development – desenvolvimento em tecnologias Microsoft"), Java_Development(
			"Java Development – desenvolvimento em Java"), Safety_Critical(
			"Safety Critical – desenvolvimento mais baixo nível (C, Assembly, VHDL,...)"), Project_Management(
			"Project Management – gestão de projectos"), Integration(
			"Integration – competências em bases de dados, integração (ESBs, protocolos de comunicação, middleware,...)");

	private String valor;

	private AreaTecnica(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return this.valor;
	}
}
