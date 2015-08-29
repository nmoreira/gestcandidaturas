package pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringNormalizerTest {

	private static final String accents = "È,É,Ê,Ë,Û,Ù,Ï,Î,À,Â,Ô,è,é,ê,ë,û,ù,ï,î,à,â,ô,Ç,ç,Ã,ã,Õ,õ";
	private static final String expected = "E,E,E,E,U,U,I,I,A,A,O,e,e,e,e,u,u,i,i,a,a,o,C,c,A,a,O,o";

	private static final String accents2 = "çÇáéíóúýÁÉÍÓÚÝàèìòùÀÈÌÒÙãõñäëïöüÿÄËÏÖÜÃÕÑâêîôûÂÊÎÔÛ";
	private static final String expected2 = "cCaeiouyAEIOUYaeiouAEIOUaonaeiouyAEIOUAONaeiouAEIOU";

	private static final String accents3 = "Gisele Bündchen da Conceição e Silva foi batizada assim em homenagem à sua conterrânea de Horizontina, RS.";
	private static final String expected3 = "Gisele Bundchen da Conceicao e Silva foi batizada assim em homenagem a sua conterranea de Horizontina, RS.";

	private static final String accents4 = "/Users/rponte/arquivos-portalfcm/Eletron/Atualização_Diária-1.23.40.exe";
	private static final String expected4 = "/Users/rponte/arquivos-portalfcm/Eletron/Atualizacao_Diaria-1.23.40.exe";

	@Test
	public void replacingAllAccents() {
		assertEquals(expected, StringNormalizer.unaccent(accents));
		assertEquals(expected2, StringNormalizer.unaccent(accents2));
		assertEquals(expected3, StringNormalizer.unaccent(accents3));
		assertEquals(expected4, StringNormalizer.unaccent(accents4));
	}

}