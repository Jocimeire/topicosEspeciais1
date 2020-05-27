package br.fepi.si.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "pista")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pista extends Evento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public double calculaPremio() {		
		return getKilometragem() * getTempoProva() * 6.00;
	}

}
