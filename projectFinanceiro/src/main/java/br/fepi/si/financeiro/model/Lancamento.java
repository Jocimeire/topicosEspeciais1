package br.fepi.si.financeiro.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;



@Entity
@Table(name = "lancamento")
public class Lancamento implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idLancamento;
	private Pessoa pessoa;
	private String descricao;
	private BigDecimal valor;
	private TipoLancamento tipoLancamento;
	private Date dataVencimento;
	private Date dataPagamento;
	
	@Id
	@GeneratedValue
	public Long getIdLancamento() {
		return idLancamento;
	}
	public void setIdLancamento(Long id) {
		this.idLancamento = id;
	}
	
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "pessoa_id")
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	@NotEmpty
	@Size(max = 80)
	@Column(length = 80, nullable =	false)
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}	
	
	@NotNull
	@DecimalMin ("0")
	@Column(precision = 10, scale = 2, nullable = false)
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}	
	
	@NotNull
	@Enumerated (EnumType.STRING)
	@Column(nullable = false)
	public TipoLancamento getTipoLancamento() {
		return tipoLancamento;
	}
	public void setTipoLancamento(TipoLancamento tipo) {
		this.tipoLancamento = tipo;
	}
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "data_vencimento", nullable = false)
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_pagamento", nullable = true)
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idLancamento == null) ? 0 : idLancamento.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lancamento other = (Lancamento) obj;
		if (idLancamento == null) {
			if (other.idLancamento != null)
				return false;
		} else if (!idLancamento.equals(other.idLancamento))
			return false;
		return true;
	}
		
}