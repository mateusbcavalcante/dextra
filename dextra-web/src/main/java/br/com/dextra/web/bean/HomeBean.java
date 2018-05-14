package br.com.dextra.web.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.dextra.cmn.util.jsf.AbstractBean;
import br.com.dextra.ngc.entity.Pedido;
import br.com.dextra.ngc.service.PedidoService;

@RequestScoped
@ManagedBean
public class HomeBean extends AbstractBean<Pedido, PedidoService>
{
	public HomeBean()
	{
		super(PedidoService.getInstancia());
		this.ACTION_SEARCH = "home";
		this.pageTitle = "Início";
	}
}