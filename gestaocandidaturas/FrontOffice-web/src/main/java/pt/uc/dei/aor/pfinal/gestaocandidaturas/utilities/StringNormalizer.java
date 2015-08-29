package pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities;

import java.text.Normalizer;

public class StringNormalizer {

	/**
	 * Remove toda a acentuação da string substituindo por caracteres simples
	 * sem acento. Fonte: https://gist.github.com/rponte/893494
	 */
	public static String unaccent(String src) {
		return Normalizer.normalize(src, Normalizer.Form.NFD).replaceAll(
				"[^\\p{ASCII}]", "");
	}

}