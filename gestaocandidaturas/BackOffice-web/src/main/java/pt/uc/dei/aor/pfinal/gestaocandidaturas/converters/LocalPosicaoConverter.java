package pt.uc.dei.aor.pfinal.gestaocandidaturas.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.LocalPosicao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.view.NovaPosicao;

@FacesConverter("localPosicao")
public class LocalPosicaoConverter implements Converter {

	@Inject
	private NovaPosicao bean;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null) {
			for (LocalPosicao local : bean.getLocais()) {
				if (value.equals(local.toString())) {
					return local;
				}
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			LocalPosicao loc = (LocalPosicao) value;
			return loc.toString();
		}
		return null;
	}

}
