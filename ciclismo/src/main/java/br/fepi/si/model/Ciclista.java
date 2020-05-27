package br.fepi.si.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "ciclista")
@Inheritance(strategy = InheritanceType.JOINED)
public class Ciclista implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long idCiclista;
	private String Sexo;
	private String nome;
	private String pais;
	
	public Ciclista(Long idCiclista, String sexo, String nome, String pais) {
		super();
		this.idCiclista = idCiclista;
		Sexo = sexo;
		this.nome = nome;
		this.pais = pais;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pessoa")
	public Long getIdCiclista() {
		return idCiclista;
	}
	
	public void setIdCiclista(Long idCiclista) {
		this.idCiclista = idCiclista;
	}
	
	@NotNull
	@Column(name = "sexo", nullable = false)
	public String getSexo() {
		return Sexo;
	}
	
	public void setSexo(String sexo) {
		this.Sexo = sexo;
	}
	
	@NotEmpty
	@Size (max = 120)
	@Column(name = "nome", nullable = false)
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@NotNull
	@DecimalMin("0")
	@Column(name = "pais", nullable = true)
	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Sexo == null) ? 0 : Sexo.hashCode());
		result = prime * result + ((idCiclista == null) ? 0 : idCiclista.hashCode());
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Ciclista other = (Ciclista) obj;
		if (Sexo == null) {
			if (other.Sexo != null)
				return false;
		} else if (!Sexo.equals(other.Sexo))
			return false;
		if (idCiclista == null) {
			if (other.idCiclista != null)
				return false;
		} else if (!idCiclista.equals(other.idCiclista))
			return false;
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}