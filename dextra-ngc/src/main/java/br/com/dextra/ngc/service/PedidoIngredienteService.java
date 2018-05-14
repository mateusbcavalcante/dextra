package br.com.dextra.ngc.service;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.com.dextra.cmn.util.DextraHbNgc;
import br.com.dextra.cmn.util.RestritorHb;
import br.com.dextra.ngc.entity.PedidoLancheIngrediente;

public class PedidoIngredienteService extends DextraHbNgc<PedidoLancheIngrediente>
{
	private static PedidoIngredienteService instancia = null;

	@SuppressWarnings("rawtypes")
	private static Map filtroPropriedade = new HashMap();
	
	@SuppressWarnings("rawtypes")
	private static Map restritores = new HashMap();
	
	public static PedidoIngredienteService getInstancia()
	{
		if (instancia == null)
		{
			instancia = new PedidoIngredienteService();
		}
		return instancia;
	}
	
	public PedidoIngredienteService()
	{
		adicionarFiltro("idPedido", RestritorHb.RESTRITOR_EQ,"idPedido");
		adicionarFiltro("idIngrediente", RestritorHb.RESTRITOR_EQ,"idIngrediente");
	}
	
	@Override
	protected Criteria montaCriteria(Session sessao, int join)
	{
		Criteria criteria = sessao.createCriteria(PedidoLancheIngrediente.class);
		return criteria;
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