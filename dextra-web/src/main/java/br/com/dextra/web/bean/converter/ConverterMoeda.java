package br.com.dextra.web.bean.converter;

import java.text.DecimalFormat;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("converterMoeda")
public class ConverterMoeda implements Converter
{
	public Object getAsObject(FacesContext contexto, UIComponent componente, String valor)
	{
		return valor.replace(".", "").replace(",", ".");
    }

	public String getAsString(FacesContext contexto, UIComponent componente, Object objeto)
	{
		return new DecimalFormat("#,##0.00").format(objeto);
	}
}