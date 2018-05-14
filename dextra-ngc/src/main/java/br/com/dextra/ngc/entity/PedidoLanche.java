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
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;

/**
 * @author Mateus Bastos
 * @since 12/05/2018
 */

@Entity
@Table(name = "tb_pedido_lanche", schema = "dextra")
@SequenceGenerator(name = "SQ_PEDIDO_LANCHE", sequenceName = "SQ_PEDIDO_PEDIDO_LANCHE", allocationSize = 1)
@Proxy(lazy = true)
public class PedidoLanche implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PEDIDO_LANCHE")
	@Column(name = "id_pedido_lanche")
	private BigInteger idPedidoLanche;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pedido", insertable = false, updatable = false)
	private Pedido pedido;
	
	@Column(name = "id_pedido")
	private BigInteger idPedido;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_lanche", insertable = false, updatable = false)
	private Lanche lanche;
	
	@Column(name = "id_lanche")
	private BigInteger idLanche;

	@Column(name = "flg_promocao")
	private String flgPromocao;

	@Column(name = "quantidade")
	private Integer quantidade;
	
	@Column(name = "valor")
	private Double valor;
	
	@Transient
	private String strValor;
	
	@Transient
	private String flgPersonalizar;
	
	@Transient
	private String descricaoPromocao;
	
	public BigInteger getIdPedidoLanche() {
		return idPedidoLanche;
	}

	public void setIdPedidoLanche(BigInteger idPedidoLanche) {
		this.idPedidoLanche = idPedidoLanche;
	}

	public BigInteger getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(BigInteger idPedido) {
		this.idPedido = idPedido;
	}

	public BigInteger getIdLanche() {
		return idLanche;
	}

	public void setIdLanche(BigInteger idLanche) {
		this.idLanche = idLanche;
	}

	public String getFlgPromocao() {
		return flgPromocao;
	}

	public void setFlgPromocao(String flgPromocao) {
		this.flgPromocao = flgPromocao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Lanche getLanche() {
		return lanche;
	}

	public void setLanche(Lanche lanche) {
		this.lanche = lanche;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getStrValor() {
		return strValor;
	}

	public void setStrValor(String strValor) {
		this.strValor = strValor;
	}

	public String getFlgPersonalizar() {
		return flgPersonalizar;
	}

	public void setFlgPersonalizar(String flgPersonalizar) {
		this.flgPersonalizar = flgPersonalizar;
	}

	public String getDescricaoPromocao() {
		return descricaoPromocao;
	}

	public void setDescricaoPromocao(String descricaoPromocao) {
		this.descricaoPromocao = descricaoPromocao;
	}
}