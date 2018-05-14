package br.com.dextra.web.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.dextra.cmn.util.jsf.AbstractBean;
import br.com.dextra.ngc.entity.Promocao;
import br.com.dextra.ngc.functions.MenuControl;
import br.com.dextra.ngc.service.PromocaoService;

@RequestScoped
@ManagedBean
public class PromocaoBean extends AbstractBean<Promocao, PromocaoService>
{	
	public PromocaoBean()
	{
		super(PromocaoService.getInstancia());
		this.ACTION_SEARCH = "promocao";
		this.pageTitle = "Promoção";
		
		MenuControl.ativarMenu("flgPromocao");
	}
}