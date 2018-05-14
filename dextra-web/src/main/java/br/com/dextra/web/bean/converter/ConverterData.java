package br.com.dextra.web.bean.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.dextra.cmn.util.jsf.JSFUtil;

@FacesConverter("converterData")
public class ConverterData implements Converter
{
	public Object getAsObject(FacesContext contexto, UIComponent componente, String valor)
	{
		Date data = JSFUtil.converteStringData(valor);
		return data;
    }

	public String getAsString(FacesContext contexto, UIComponent componente, Object objeto)
	{
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		return formato.format(objeto);
	}
}