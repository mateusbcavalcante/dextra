package br.com.dextra.ngc.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;

/**
 * @author Mateus Bastos
 * @since 12/05/2018
 */

@Entity
@Table(name = "tb_lanche", schema = "dextra")
@SequenceGenerator(name = "SQ_LANCHE", sequenceName = "SQ_LANCHE", allocationSize = 1)
@Proxy(lazy = true)
public class Lanche implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_LANCHE")
	@Column(name = "id_lanche")
	private BigInteger idLanche;

	@Column(name = "descricao")
	private String descricao;
	
	@OneToMany(mappedBy="lanche", fetch = FetchType.LAZY)
    private List<LancheIngrediente> listIngrediente;
	
	@Transient
	private String strIngrediente;
	
	@Transient
	private String strValor;
	
	@Transient
	private Integer quantidade;
	
	public BigInteger getIdLanche() {
		return idLanche;
	}

	public void setIdLanche(BigInteger idLanche) {
		this.idLanche = idLanche;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStrIngrediente() {
		return strIngrediente;
	}

	public void setStrIngrediente(String strIngrediente) {
		this.strIngrediente = strIngrediente;
	}

	public String getStrValor() {
		return strValor;
	}

	public void setStrValor(String strValor) {
		this.strValor = strValor;
	}

	public List<LancheIngrediente> getListIngrediente() {
		return listIngrediente;
	}

	public void setListIngrediente(List<LancheIngrediente> listIngrediente) {
		this.listIngrediente = listIngrediente;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}