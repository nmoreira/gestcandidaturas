package pt.uc.dei.aor.pfinal.gestaocandidaturas.view;

public enum Theme {
	afternoon("afternoon", "Afternoon"), afterwork("afterwork", "Afterwork"), aristo(
			"aristo", "Aristo"), bluesky("bluesky", "Bluesky"), bootstrap(
			"bootstrap", "Bootstrap"), casablanca("casablanca", "Casablanca"), cupertino(
			"cupertino", "Cupertino"), delta("delta", "Delta"), excite_bike(
			"excite-bike", "Excite-Bike"), flick("flick", "Flick"), glass_x(
			"glass-x", "Glass-X"), le_frog("le-frog", "Le-Frog"), overcast(
			"overcast", "Overcast"), pepper_grinder("pepper-grinder",
			"Pepper-Grinder"), sam("sam", "Sam"), smoothness("smoothness",
			"Smoothness"), south_street("south-street", "South-Street");

	private String name;
	private String desc;

	private Theme(String name, String desc) {
		this.name = name;
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}

}
