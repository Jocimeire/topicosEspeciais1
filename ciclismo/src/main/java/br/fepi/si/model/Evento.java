package br.fepi.si.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "evento")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Evento implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	private Long idEvento;
	private String nomeEventro;
	private Date data;
	private String local;
	private double kilometragem;
	private double tempoProva;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_evento")
	public Long getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(Long idEvento) {
		this.idEvento = idEvento;
	}
	
	@NotEmpty
	@Size (max = 120)
	@Column(name = "nomeEventro", nullable = false)
	public String getNome() {
		return nomeEventro;
	}
		
	public void setNome(String nomeEventro) {
		this.nomeEventro = nomeEventro;
	}
	
	@Column(name = "data", nullable = false)
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	@NotEmpty
	@Size (max = 50)
	@Column(name = "local", nullable = false)
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	
	@Column(name = "kilometragem", nullable = false)
	public double getKilometragem() {
		return kilometragem;
	}
	public void setKilometragem(double kilometragem) {
		this.kilometragem = kilometragem;
	}
	
	@Column(name = "tempo_prova", nullable = false)
	public double getTempoProva() {
		return tempoProva;
	}
	public void setTempoProva(double tempoProva) {
		this.tempoProva = tempoProva;
	}
	
	public double calculaPremio() {
				return 0;
	}
	
		

}
