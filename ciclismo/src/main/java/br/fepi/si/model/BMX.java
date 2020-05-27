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
@Table(name = "bmx")
@Inheritance(strategy = InheritanceType.JOINED)
public class BMX extends Evento implements Serializable{
	

	private static final long serialVersionUID = 1L;

	@Override
	public double calculaPremio() {		
		return getKilometragem() * getTempoProva() * 6.00;
	}

}
