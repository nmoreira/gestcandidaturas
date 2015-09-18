package pt.uc.dei.aor.pfinal.gestaocandidaturas.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.GuiaoService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.PosicaoService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Guiao;

@FacesConverter("guiaoConverter")
public class GuiaoConverter implements Converter {

	@Inject
	private GuiaoService guiaoServ;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null) {
			for (Guiao guiao : guiaoServ.getGuioesDisponiveis()) {
				if (Long.parseLong(value) == guiao.getId())
					return guiao;
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Guiao guiao = (Guiao) value;
			return Long.toString(guiao.getId());
		}
		return null;
	}

}
