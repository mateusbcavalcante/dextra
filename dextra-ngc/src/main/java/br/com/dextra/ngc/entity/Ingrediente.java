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
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;

/**
 * @author Mateus Bastos
 * @since 12/05/2018
 */

@Entity
@Table(name = "tb_ingrediente", schema = "dextra")
@SequenceGenerator(name = "SQ_INGREDIENTE", sequenceName = "SQ_INGREDIENTE", allocationSize = 1)
@Proxy(lazy = true)
public class Ingrediente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_INGREDIENTE")
	@Column(name = "id_ingrediente")
	private BigInteger idIngrediente;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "valor")
	private Double valor;
	
	@Transient
	private Integer quantidade;
	
	@Transient
	private String strValor;

	public BigInteger getIdIngrediente() {
		return idIngrediente;
	}

	public void setIdIngrediente(BigInteger idIngrediente) {
		this.idIngrediente = idIngrediente;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getStrValor() {
		return strValor;
	}

	public void setStrValor(String strValor) {
		this.strValor = strValor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}