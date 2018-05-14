package br.com.dextra.ngc.service;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.com.dextra.cmn.util.DextraHbNgc;
import br.com.dextra.cmn.util.RestritorHb;
import br.com.dextra.ngc.entity.LancheIngrediente;

public class LancheIngredienteService extends DextraHbNgc<LancheIngrediente>
{
	private static LancheIngredienteService instancia = null;

	public static final int JOIN_LANCHE = 1;
	
	public static final int JOIN_INGREDIENTE = 2;
	
	@SuppressWarnings("rawtypes")
	private static Map filtroPropriedade = new HashMap();
	
	@SuppressWarnings("rawtypes")
	private static Map restritores = new HashMap();
	
	public static LancheIngredienteService getInstancia()
	{
		if (instancia == null)
		{
			instancia = new LancheIngredienteService();
		}
		return instancia;
	}
	
	public LancheIngredienteService()
	{
		adicionarFiltro("idLanche", RestritorHb.RESTRITOR_EQ,"idLanche");
		adicionarFiltro("idIngrediente", RestritorHb.RESTRITOR_EQ,"idIngrediente");
	}
	
	@Override
	protected Criteria montaCriteria(Session sessao, int join)
	{
		Criteria criteria = sessao.createCriteria(LancheIngrediente.class);
		
		if ((join & JOIN_LANCHE) != 0)
		{
			criteria.createAlias("lanche", "lanche");
		}
		
		if ((join & JOIN_INGREDIENTE) != 0)
		{
			criteria.createAlias("ingrediente", "ingrediente");
		}
		
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