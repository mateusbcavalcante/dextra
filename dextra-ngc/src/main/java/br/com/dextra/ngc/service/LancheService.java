package br.com.dextra.ngc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.sql.JoinType;

import com.ibm.icu.text.DecimalFormat;

import br.com.dextra.cmn.util.DextraHbNgc;
import br.com.dextra.cmn.util.HibernateUtil;
import br.com.dextra.cmn.util.RestritorHb;
import br.com.dextra.ngc.entity.Lanche;
import br.com.dextra.ngc.entity.LancheIngrediente;

public class LancheService extends DextraHbNgc<Lanche>
{
	private static LancheService instancia = null;
	
	public static final int JOIN_LANCHE_INGREDIENTE = 1;
	
	public static final int JOIN_LANCHE_INGREDIENTE_INGREDIENTE = 2;

	@SuppressWarnings("rawtypes")
	private static Map filtroPropriedade = new HashMap();
	
	@SuppressWarnings("rawtypes")
	private static Map restritores = new HashMap();
	
	public static LancheService getInstancia()
	{
		if (instancia == null)
		{
			instancia = new LancheService();
		}
		return instancia;
	}
	
	public LancheService()
	{
		adicionarFiltro("idLanche", RestritorHb.RESTRITOR_EQ,"idLanche");
		adicionarFiltro("descricao", RestritorHb.RESTRITOR_LIKE, "descricao");
	}
	
	@Override
	protected Criteria montaCriteria(Session sessao, int join)
	{
		Criteria criteria = sessao.createCriteria(Lanche.class);
		
		 if ((join & JOIN_LANCHE_INGREDIENTE) != 0)
		 {
			 criteria.createAlias("listIngrediente", "listIngrediente", JoinType.LEFT_OUTER_JOIN);
			 
			 if ((join & JOIN_LANCHE_INGREDIENTE_INGREDIENTE) != 0)
			 {
	        	 criteria.createAlias("listIngrediente.ingrediente", "ingrediente");
			 }
		 }
		 
		return criteria;
	}
	
	@Override
	protected void setarOrdenacao(Criteria criteria, Lanche vo, int join)
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
	
	public List<Lanche> pesquisarLanche() throws Exception
	{
		Session sessao = HibernateUtil.getSession();
		sessao.setFlushMode(FlushMode.COMMIT);
		try
		{
			return this.pesquisarLanche(sessao);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			sessao.close();
		}
	}

	private List<Lanche> pesquisarLanche(Session sessao) throws Exception 
	{
		List<Lanche> listLanche = super.pesquisar(new Lanche(), 0);
		
		if (listLanche != null && listLanche.size() > 0)
		{
			for (Lanche lanche : listLanche) 
			{
				Double valor = 0.0;
				String conector = "";
				String strIngrediente = "";
				
				LancheIngrediente lancheIngrediente = new LancheIngrediente();
				lancheIngrediente.setIdLanche(lanche.getIdLanche());
				
				List<LancheIngrediente> listLancheIngrediente = LancheIngredienteService.getInstancia().pesquisar(sessao, lancheIngrediente, LancheIngredienteService.JOIN_INGREDIENTE);
				
				if (listLancheIngrediente != null && listLancheIngrediente.size() > 0)
				{
					for (LancheIngrediente element : listLancheIngrediente) 
					{
						valor += element.getIngrediente().getValor();
						strIngrediente += conector + element.getIngrediente().getDescricao();
						conector = ", ";
					}
				}
				
				lanche.setStrIngrediente("(".concat(strIngrediente.concat(")")));
				lanche.setStrValor(new DecimalFormat("#,##0.00").format(valor));
			}
		}
		return listLanche;
	}
}