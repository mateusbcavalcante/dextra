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
@Table(name = "tb_lanche_ingrediente", schema = "dextra")
@SequenceGenerator(name = "SQ_LANCHE_INGREDIENTE", sequenceName = "SQ_LANCHE_INGREDIENTE", allocationSize = 1)
@Proxy(lazy = true)
public class LancheIngrediente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_LANCHE_INGREDIENTE")
	@Column(name = "id_lanche_ingrediente")
	private BigInteger idLancheIngrediente;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_lanche", insertable = false, updatable = false)
	private Lanche lanche;
	
	@Column(name = "id_lanche")
	private BigInteger idLanche;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ingrediente", insertable = false, updatable = false)
	private Ingrediente ingrediente;
	
	@Column(name = "id_ingrediente")
	private BigInteger idIngrediente;
	
	public BigInteger getIdLancheIngrediente() {
		return idLancheIngrediente;
	}

	public void setIdLancheIngrediente(BigInteger idLancheIngrediente) {
		this.idLancheIngrediente = idLancheIngrediente;
	}

	public BigInteger getIdLanche() {
		return idLanche;
	}

	public void setIdLanche(BigInteger idLanche) {
		this.idLanche = idLanche;
	}

	public BigInteger getIdIngrediente() {
		return idIngrediente;
	}

	public void setIdIngrediente(BigInteger idIngrediente) {
		this.idIngrediente = idIngrediente;
	}

	public Lanche getLanche() {
		return lanche;
	}

	public void setLanche(Lanche lanche) {
		this.lanche = lanche;
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}
}