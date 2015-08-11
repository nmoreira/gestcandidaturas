package pt.uc.dei.aor.pfinal.gestaocandidaturas.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.ResponsavelPosicao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.view.NovaPosicao;

@FacesConverter("responsavelPosicao")
public class ResponsavelPosicaoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null) {
			for (ResponsavelPosicao responsavel : NovaPosicao.getResponsaveis()) {
				if (value.equals(String.valueOf(responsavel.getId()))) {
					return responsavel;
				}
			}
		}
		return null;
		// ResponsavelPosicao r =
		// context.getApplication().getELResolver().getValue(context.getELContext(),
		// null, "responsavel")). .find(id)
		// ResponsavelPosicao resp = respSrv.getResponsavelPosicaoById(value);
		// System.out.println("foi lido o responsavel " + resp.getNome());
		// return resp;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			ResponsavelPosicao resp = (ResponsavelPosicao) value;
			return "" + resp.getId();
			// return String.valueOf(((ResponsavelPosicao) value).getId());
		}
		return null;
	}

}
