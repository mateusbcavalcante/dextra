package br.com.dextra.web.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.dextra.cmn.util.jsf.AbstractBean;
import br.com.dextra.ngc.entity.Ingrediente;
import br.com.dextra.ngc.functions.MenuControl;
import br.com.dextra.ngc.service.IngredienteService;

@RequestScoped
@ManagedBean
public class IngredienteBean extends AbstractBean<Ingrediente, IngredienteService>
{	
	public IngredienteBean()
	{
		super(IngredienteService.getInstancia());
		this.ACTION_SEARCH = "ingrediente";
		this.pageTitle = "Ingrediente";
		
		MenuControl.ativarMenu("flgIngrediente");
	}
}