package br.com.dextra.ngc.service;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.dextra.cmn.util.DextraHbNgc;
import br.com.dextra.cmn.util.RestritorHb;
import br.com.dextra.ngc.entity.Ingrediente;

public class IngredienteService extends DextraHbNgc<Ingrediente>
{
	private static IngredienteService instancia = null;

	@SuppressWarnings("rawtypes")
	private static Map filtroPropriedade = new HashMap();
	
	@SuppressWarnings("rawtypes")
	private static Map restritores = new HashMap();
	
	public static IngredienteService getInstancia()
	{
		if (instancia == null)
		{
			instancia = new IngredienteService();
		}
		return instancia;
	}
	
	public IngredienteService()
	{
		adicionarFiltro("idIngrediente", RestritorHb.RESTRITOR_EQ,"idIngrediente");
		adicionarFiltro("descricao", RestritorHb.RESTRITOR_LIKE, "descricao");
	}
	
	@Override
	protected Criteria montaCriteria(Session sessao, int join)
	{
		Criteria criteria = sessao.createCriteria(Ingrediente.class);
		return criteria;
	}
	
	@Override
	protected void setarOrdenacao(Criteria criteria, Ingrediente vo, int join)
	{
		criteria.addOrder(Order.asc("descricao"));
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