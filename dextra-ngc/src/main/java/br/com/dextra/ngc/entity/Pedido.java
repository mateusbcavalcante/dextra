package br.com.dextra.ngc.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

/**
 * @author Mateus Bastos
 * @since 12/05/2018
 */

@Entity
@Table(name = "tb_pedido", schema = "dextra")
@SequenceGenerator(name = "SQ_PEDIDO", sequenceName = "SQ_PEDIDO", allocationSize = 1)
@Proxy(lazy = true)
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PEDIDO")
	@Column(name = "id_pedido")
	private BigInteger idPedido;

	@Column(name = "valor")
	private Double valor;

	public BigInteger getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(BigInteger idPedido) {
		this.idPedido = idPedido;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
}