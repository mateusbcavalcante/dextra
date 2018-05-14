package br.com.dextra.web.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.dextra.cmn.util.jsf.AbstractBean;
import br.com.dextra.ngc.entity.Lanche;
import br.com.dextra.ngc.functions.MenuControl;
import br.com.dextra.ngc.service.LancheService;

@RequestScoped
@ManagedBean
public class LancheBean extends AbstractBean<Lanche, LancheService>
{	
	public LancheBean()
	{
		super(LancheService.getInstancia());
		this.ACTION_SEARCH = "lanche";
		this.pageTitle = "Lanche";
		
		MenuControl.ativarMenu("flgLanche");
	}
}