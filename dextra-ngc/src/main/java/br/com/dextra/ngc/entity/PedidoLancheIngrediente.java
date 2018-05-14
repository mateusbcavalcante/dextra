package br.com.dextra.ngc.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

/**
 * @author Mateus Bastos
 * @since 12/05/2018
 */

@Entity
@Table(name = "tb_pedido_lanche_ingrediente", schema = "dextra")
@SequenceGenerator(name = "SQ_PEDIDO_LANCHE_INGREDIENTE", sequenceName = "SQ_PEDIDO_LANCHE_INGREDIENTE", allocationSize = 1)
@Proxy(lazy = true)
public class PedidoLancheIngrediente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PEDIDO_LANCHE_INGREDIENTE")
	@Column(name = "id_pedido_lanche_ingrediente")
	private BigInteger idPedidoIngrediente;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pedido_lanche", insertable = false, updatable = false)
	private PedidoLanche pedidoLanche;
	
	@Column(name = "id_pedido_lanche")
	private BigInteger idPedidoLanche;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ingrediente", insertable = false, updatable = false)
	private Ingrediente ingrediente;
	
	@Column(name = "id_ingrediente")
	private BigInteger idIngrediente;
	
	@Column(name = "quantidade")
	private Integer quantidade;

	public BigInteger getIdPedidoIngrediente() {
		return idPedidoIngrediente;
	}

	public void setIdPedidoIngrediente(BigInteger idPedidoIngrediente) {
		this.idPedidoIngrediente = idPedidoIngrediente;
	}

	public BigInteger getIdPedidoLanche() {
		return idPedidoLanche;
	}

	public void setIdPedidoLanche(BigInteger idPedidoLanche) {
		this.idPedidoLanche = idPedidoLanche;
	}

	public BigInteger getIdIngrediente() {
		return idIngrediente;
	}

	public void setIdIngrediente(BigInteger idIngrediente) {
		this.idIngrediente = idIngrediente;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public PedidoLanche getPedidoLanche() {
		return pedidoLanche;
	}

	public void setPedidoLanche(PedidoLanche pedidoLanche) {
		this.pedidoLanche = pedidoLanche;
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}
}