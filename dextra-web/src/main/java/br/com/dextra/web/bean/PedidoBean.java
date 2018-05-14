package br.com.dextra.web.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.dextra.cmn.util.jsf.AbstractBean;
import br.com.dextra.ngc.entity.Pedido;
import br.com.dextra.ngc.functions.MenuControl;
import br.com.dextra.ngc.service.PedidoService;

@RequestScoped
@ManagedBean
public class PedidoBean extends AbstractBean<Pedido, PedidoService>
{	
	public PedidoBean()
	{
		super(PedidoService.getInstancia());
		this.ACTION_SEARCH = "pedido";
		this.pageTitle = "Pedido";
		
		MenuControl.ativarMenu("flgPedido");
	}
}