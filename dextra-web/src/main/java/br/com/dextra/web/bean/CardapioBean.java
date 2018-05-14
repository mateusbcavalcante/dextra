package br.com.dextra.web.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.ibm.icu.text.DecimalFormat;

import br.com.dextra.cmn.util.jsf.AbstractBean;
import br.com.dextra.ngc.entity.Ingrediente;
import br.com.dextra.ngc.entity.Lanche;
import br.com.dextra.ngc.entity.Pedido;
import br.com.dextra.ngc.entity.PedidoLanche;
import br.com.dextra.ngc.service.IngredienteService;
import br.com.dextra.ngc.service.LancheService;
import br.com.dextra.ngc.service.PedidoService;
import br.com.dextra.ngc.service.PromocaoService;

@RequestScoped
@ManagedBean
public class CardapioBean extends AbstractBean<Pedido, PedidoService>
{	
	private List<Lanche> listLanche;
	
	private List<Ingrediente> listIngrediente;
	
	private List<PedidoLanche> listPedidoLanche;
	
	private String strValorPedidoTotal = "0,00";
	
	private Lanche lanche;
	
	public CardapioBean()
	{
		super(PedidoService.getInstancia());
		this.ACTION_SEARCH = "cardapio";
		this.pageTitle = "Cardápio";
	}
	
	@Override
	protected void setValoresDefault() throws Exception
	{
		listLanche = LancheService.getInstancia().pesquisarLanche();
		
		listIngrediente = IngredienteService.getInstancia().pesquisar(new Ingrediente(), 0);
		
		for (Ingrediente ingrediente : listIngrediente) 
		{
			ingrediente.setStrValor(new DecimalFormat("#,##0.00").format(ingrediente.getValor()));
		}
	}

	public void processarPedido(Lanche lanche) 
	{
		Double valorPedidoTotal = 0.0;
		boolean existeLanche = false;
		
		if (listPedidoLanche == null || listPedidoLanche.size() <= 0)
		{
			listPedidoLanche = new ArrayList<>();
		} 
		else
		{
			if (lanche.getQuantidade() == null || lanche.getQuantidade().intValue() <= 0) 
			{
				PedidoLanche pedidoLancheDeletado = null;
				
				for (PedidoLanche pedidoLanche : listPedidoLanche)
				{
					if (pedidoLanche.getLanche().getIdLanche().intValue() == lanche.getIdLanche().intValue()) 
					{
						existeLanche = true;
						pedidoLancheDeletado = pedidoLanche;
					}
					else
					{
						valorPedidoTotal += pedidoLanche.getValor();
						strValorPedidoTotal = new DecimalFormat("#,##0.00").format(valorPedidoTotal);
					}
				}
				
				if (existeLanche) 
				{
					listPedidoLanche.remove(pedidoLancheDeletado);
				}
			}
			else
			{
				for (PedidoLanche pedidoLanche : listPedidoLanche)
				{
					if (pedidoLanche.getFlgPersonalizar().equalsIgnoreCase("N") && pedidoLanche.getLanche().getIdLanche().intValue() == lanche.getIdLanche().intValue()) 
					{
						pedidoLanche.setQuantidade(lanche.getQuantidade());
						pedidoLanche.setValor(lanche.getQuantidade() * Double.parseDouble(lanche.getStrValor().replace(",", ".")));
						pedidoLanche.setStrValor(new DecimalFormat("#,##0.00").format(pedidoLanche.getValor()));
						
						existeLanche = true;
					}
					
					valorPedidoTotal += pedidoLanche.getValor();
					strValorPedidoTotal = new DecimalFormat("#,##0.00").format(valorPedidoTotal);
				}
			}
		}
		
		if (!existeLanche)
		{
			if (lanche.getQuantidade() != null && lanche.getQuantidade() > 0) 
			{
				PedidoLanche pedidoLanche = new PedidoLanche();
				pedidoLanche.setQuantidade(lanche.getQuantidade());
				pedidoLanche.setLanche(lanche);
				pedidoLanche.setValor(lanche.getQuantidade() * Double.parseDouble(lanche.getStrValor().replace(",", ".")));
				pedidoLanche.setStrValor(new DecimalFormat("#,##0.00").format(pedidoLanche.getValor()));
				pedidoLanche.setFlgPersonalizar("N");
				pedidoLanche.setFlgPromocao("N");
				
				listPedidoLanche.add(pedidoLanche);
				
				valorPedidoTotal += pedidoLanche.getValor();
				strValorPedidoTotal = new DecimalFormat("#,##0.00").format(valorPedidoTotal);
			}
		}
	}
	
	public void personalizar() throws Exception 
	{
		Double valorPedidoTotal = 0.0;
		boolean existeIngrediente = false;
		
		if (lanche != null) 
		{
			Double valorTotalIngrediente = 0.0;

			for (Ingrediente ingrediente : listIngrediente)
			{
				if (ingrediente.getQuantidade() != null && ingrediente.getQuantidade() > 0) 
				{
					valorTotalIngrediente += ingrediente.getQuantidade() * ingrediente.getValor();
					existeIngrediente = true;
				}
			}
			
			if (existeIngrediente)
			{
				PedidoLanche pedidoLanche = new PedidoLanche();
				pedidoLanche.setQuantidade(1);
				pedidoLanche.setLanche(lanche);
				pedidoLanche.setValor(valorTotalIngrediente);
				pedidoLanche.setStrValor(new DecimalFormat("#,##0.00").format(valorTotalIngrediente));
				pedidoLanche.setFlgPersonalizar("S");
				pedidoLanche.setFlgPromocao("N");
				
				pedidoLanche = PromocaoService.getInstancia().processar(pedidoLanche, listIngrediente);
				
				if (listPedidoLanche == null || listPedidoLanche.size() <= 0)
				{
					listPedidoLanche = new ArrayList<>();
				}
				
				listPedidoLanche.add(pedidoLanche);
			}
		}
		
		if (listPedidoLanche != null && listPedidoLanche.size() > 0) 
		{
			for (PedidoLanche pedidoLanche : listPedidoLanche)
			{
				valorPedidoTotal += pedidoLanche.getValor();
			}
		}
		
		strValorPedidoTotal = new DecimalFormat("#,##0.00").format(valorPedidoTotal);
		this.setValoresDefault();
	}
	
	public List<Lanche> getListLanche() 
	{
		return listLanche;
	}

	public void setListLanche(List<Lanche> listLanche) 
	{
		this.listLanche = listLanche;
	}

	public List<Ingrediente> getListIngrediente() {
		return listIngrediente;
	}

	public void setListIngrediente(List<Ingrediente> listIngrediente) {
		this.listIngrediente = listIngrediente;
	}

	public List<PedidoLanche> getListPedidoLanche() {
		return listPedidoLanche;
	}

	public void setListPedidoLanche(List<PedidoLanche> listPedidoLanche) {
		this.listPedidoLanche = listPedidoLanche;
	}

	public String getStrValorPedidoTotal() {
		return strValorPedidoTotal;
	}

	public void setStrValorPedidoTotal(String strValorPedidoTotal) {
		this.strValorPedidoTotal = strValorPedidoTotal;
	}

	public Lanche getLanche() {
		return lanche;
	}

	public void setLanche(Lanche lanche) {
		this.lanche = lanche;
	}
}