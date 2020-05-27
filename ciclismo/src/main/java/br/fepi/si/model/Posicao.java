package br.fepi.si.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "posicao")
@Inheritance(strategy = InheritanceType.JOINED)
public class Posicao {
	
	private int posicao;
	private Evento prova;
	private Ciclista competidor;
	
	@Column(name = "posicao", nullable = false)
	public int getposicao() {
		return posicao;
	}
	public void setposicao(int posicao) {
		this.posicao = posicao;
	}
	
	@Column(name = "prova", nullable = false)
	public Evento getProva() {
		return prova;
	}
	public void setProva(Evento prova) {
		this.prova = prova;
	}
	
	@Column(name = "competidor", nullable = false)
	public Ciclista getCompetidor() {
		return competidor;
	}
	public void setCompetidor(Ciclista competidor) {
		this.competidor = competidor;
	}
	
	
}
