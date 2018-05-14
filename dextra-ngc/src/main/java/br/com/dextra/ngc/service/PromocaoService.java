package br.com.dextra.ngc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.ibm.icu.text.DecimalFormat;

import br.com.dextra.cmn.util.DextraHbNgc;
import br.com.dextra.cmn.util.RestritorHb;
import br.com.dextra.ngc.entity.Ingrediente;
import br.com.dextra.ngc.entity.PedidoLanche;
import br.com.dextra.ngc.entity.Promocao;

public class PromocaoService extends DextraHbNgc<Promocao>
{
	private static PromocaoService instancia = null;
	
	private static final String LIGHT = "LIGHT";
	private static final String MUITA_CARNE = "MUITA CARNE";
	private static final String MUITO_QUEIJO = "MUITO QUEIJO";
	
	private boolean existeAlface = false;
	private boolean existeBacon = false;
	private boolean existeOvo = false;
	private boolean existeQueijo = false;
	private boolean existeHamburguerCarne = false;
	private boolean achouPromocao = false;
	
	private HashMap<String,Integer> map = new HashMap<>();
	private Double valorTotalIngrediente = 0.0;

	@SuppressWarnings("rawtypes")
	private static Map filtroPropriedade = new HashMap();
	
	@SuppressWarnings("rawtypes")
	private static Map restritores = new HashMap();
	
	public static PromocaoService getInstancia()
	{
		if (instancia == null)
		{
			instancia = new PromocaoService();
		}
		return instancia;
	}
	
	public PromocaoService()
	{
		adicionarFiltro("idPromocao", RestritorHb.RESTRITOR_EQ,"idPromocao");
		adicionarFiltro("descricao", RestritorHb.RESTRITOR_LIKE, "descricao");
	}
	
	@Override
	protected Criteria montaCriteria(Session sessao, int join)
	{
		Criteria criteria = sessao.createCriteria(Promocao.class);
		return criteria;
	}
	
	@Override
	protected void setarOrdenacao(Criteria criteria, Promocao vo, int join)
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
	
	public PedidoLanche processar(PedidoLanche pedidoLanche, List<Ingrediente> listIngrediente) throws Exception 
	{
		this.processarIngredientes(listIngrediente);
		return this.processarPromocoes(pedidoLanche, listIngrediente);
	}

	private void processarIngredientes(List<Ingrediente> listIngrediente) 
	{
		map = new HashMap<>();
		existeAlface = false;
		existeBacon = false;
		existeOvo = false;
		existeQueijo = false;
		existeHamburguerCarne = false;
		
		for (Ingrediente ingrediente : listIngrediente) 
		{
			achouPromocao = false;
			
			if (ingrediente.getQuantidade() != null && ingrediente.getQuantidade().intValue() > 0)
			{
				switch (ingrediente.getDescricao()) 
				{
					case "Alface": 
						existeAlface = true;
						map.put("Alface", ingrediente.getQuantidade());
						break;
						
					case "Bacon":
						existeBacon = true; 
						map.put("Bacon", ingrediente.getQuantidade());
						break;
						
					case "Ovo":
						existeOvo = true; 
						map.put("Ovo", ingrediente.getQuantidade());
						break;
						
					case "Queijo":
						existeQueijo = true; 
						map.put("Queijo", ingrediente.getQuantidade());
						break;
						
					case "Hambúrguer de carne":
						existeHamburguerCarne = true; 
						map.put("Hambúrguer de carne", ingrediente.getQuantidade());
						break;
				
					default: 
						break;
				}
			}
		}
	}

	private PedidoLanche processarPromocoes(PedidoLanche pedidoLanche, List<Ingrediente> listIngrediente) throws Exception 
	{
		pedidoLanche = this.processarPromocaoLight(pedidoLanche, listIngrediente);
		pedidoLanche = this.processarPromocaoMuitaCarneQueijo(pedidoLanche, listIngrediente, "Hambúrguer de carne", MUITA_CARNE, existeHamburguerCarne);
		pedidoLanche = this.processarPromocaoMuitaCarneQueijo(pedidoLanche, listIngrediente, "Queijo", MUITO_QUEIJO, existeQueijo);
		
		return pedidoLanche;
	}

	private PedidoLanche processarPromocaoLight(PedidoLanche pedidoLanche, List<Ingrediente> listIngrediente) throws Exception 
	{
		if (!achouPromocao && existeAlface && !existeBacon) 
		{
			pedidoLanche.setDescricaoPromocao(LIGHT);
			pedidoLanche.setFlgPromocao("S");
			pedidoLanche.setValor(pedidoLanche.getValor() - (pedidoLanche.getValor() * 0.10));
			pedidoLanche.setStrValor(new DecimalFormat("#,##0.00").format(pedidoLanche.getValor()));
			achouPromocao = true;
		}
		
		return pedidoLanche;
	}
	
	private PedidoLanche processarPromocaoMuitaCarneQueijo(PedidoLanche pedidoLanche, List<Ingrediente> listIngrediente, String descricaoIngrediente, String descricaoPromocao, boolean existeIngrediente)
	{
		if (!achouPromocao && map.containsKey(descricaoIngrediente))
		{
			Integer quantidade = ((Integer) map.get(descricaoIngrediente)).intValue();
			
			if (existeIngrediente && quantidade >= 3) 
			{
				valorTotalIngrediente = 0.0;
				
				for (Ingrediente ingrediente : listIngrediente)
				{
					Integer novaQuantidade = ingrediente.getQuantidade();
					
					if (ingrediente.getDescricao().equalsIgnoreCase(descricaoIngrediente)) 
					{
						Integer resultado = (quantidade * 2) % 3;
						Integer valorReal = (quantidade / 3 * 2);
						
						if (resultado == 0) 
						{
							novaQuantidade = valorReal;
						}
						else
						{
							Integer ultimoMultiploPossivel = 0;
							
							for(int i = 0; i <= quantidade; i++)
							{
								if (i % 3 == 0)
								{
									ultimoMultiploPossivel = i;
								}
							}
							novaQuantidade = (valorReal) + (quantidade - ultimoMultiploPossivel);
						}
					}
					
					if (novaQuantidade != null && novaQuantidade.intValue() > 0) 
					{
						valorTotalIngrediente += novaQuantidade * ingrediente.getValor();
					}
				}
				
				pedidoLanche.setDescricaoPromocao(descricaoPromocao);
				pedidoLanche.setFlgPromocao("S");
				pedidoLanche.setValor(valorTotalIngrediente);
				pedidoLanche.setStrValor(new DecimalFormat("#,##0.00").format(pedidoLanche.getValor()));
				
				achouPromocao = true;
			}
		}
	
		return pedidoLanche;
	}
}