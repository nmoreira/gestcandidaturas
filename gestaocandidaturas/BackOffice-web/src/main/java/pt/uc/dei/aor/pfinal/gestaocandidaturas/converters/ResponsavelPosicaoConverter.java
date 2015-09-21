package pt.uc.dei.aor.pfinal.gestaocandidaturas.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.ResponsavelPosicaoService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.ResponsavelPosicao;

@FacesConverter("responsavelPosicao")
public class ResponsavelPosicaoConverter implements Converter {

	@Inject
	ResponsavelPosicaoService respServ;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null) {
			for (ResponsavelPosicao responsavel : respServ
					.getAllResponsaveisPosicao()) {
				if (value.equals(String.valueOf(responsavel.getId()))) {
					return responsavel;
				}
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			ResponsavelPosicao resp = (ResponsavelPosicao) value;
			return "" + resp.getId();
		}
		return null;
	}

}
