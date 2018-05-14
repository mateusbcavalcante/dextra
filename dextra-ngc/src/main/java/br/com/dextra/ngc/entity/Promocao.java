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
@Table(name = "tb_promocao", schema = "dextra")
@SequenceGenerator(name = "SQ_PROMOCAO", sequenceName = "SQ_PROMOCAO", allocationSize = 1)
@Proxy(lazy = true)
public class Promocao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PROMOCAO")
	@Column(name = "id_promocao")
	private BigInteger idPromocao;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "classe_regra")
	private String classeRegra;

	public BigInteger getIdPromocao() {
		return idPromocao;
	}

	public void setIdPromocao(BigInteger idPromocao) {
		this.idPromocao = idPromocao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getClasseRegra() {
		return classeRegra;
	}

	public void setClasseRegra(String classeRegra) {
		this.classeRegra = classeRegra;
	}
}