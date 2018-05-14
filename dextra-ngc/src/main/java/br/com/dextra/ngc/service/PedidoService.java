package br.com.dextra.ngc.service;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.dextra.cmn.util.DextraHbNgc;
import br.com.dextra.cmn.util.RestritorHb;
import br.com.dextra.ngc.entity.Pedido;

public class PedidoService extends DextraHbNgc<Pedido>
{
	private static PedidoService instancia = null;

	@SuppressWarnings("rawtypes")
	private static Map filtroPropriedade = new HashMap();
	
	@SuppressWarnings("rawtypes")
	private static Map restritores = new HashMap();
	
	public static PedidoService getInstancia()
	{
		if (instancia == null)
		{
			instancia = new PedidoService();
		}
		return instancia;
	}
	
	public PedidoService()
	{
		adicionarFiltro("idPedido", RestritorHb.RESTRITOR_EQ,"idPedido");
		adicionarFiltro("idLanche", RestritorHb.RESTRITOR_EQ,"idLanche");
		adicionarFiltro("flgPromocao", RestritorHb.RESTRITOR_EQ,"flgPromocao");
	}
	
	@Override
	protected Criteria montaCriteria(Session sessao, int join)
	{
		Criteria criteria = sessao.createCriteria(Pedido.class);
		return criteria;
	}
	
	@Override
	protected void setarOrdenacao(Criteria criteria, Pedido vo, int join)
	{
		criteria.addOrder(Order.desc("idPedido"));
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	protected Map restritores() 
	{
		return restritores;
	}

	@Override
	@SuppressWarnings("rawtypes")
	protected Map filtroPropriedade() 
	{
		return filtroPropriedade;
	}
}