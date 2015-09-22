package pt.uc.dei.aor.pfinal.gestaocandidaturas.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.GrupoEntrevistadoresService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.GrupoEntrevistadores;

@FacesConverter("grupoEntrevistadores")
public class GrupoEntrevistadoresConverter implements Converter {

	@Inject
	GrupoEntrevistadoresService entrevistadoresServ;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null) {
			for (GrupoEntrevistadores entrevistador : entrevistadoresServ
					.getAllEntrevistadores()) {
				if (value.equals(String.valueOf(entrevistador.getId()))) {
					return entrevistador;
				}
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			GrupoEntrevistadores resp = (GrupoEntrevistadores) value;
			return "" + resp.getId();
		}
		return null;
	}

}
