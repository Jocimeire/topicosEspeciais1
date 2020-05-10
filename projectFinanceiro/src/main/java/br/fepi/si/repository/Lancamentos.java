package br.fepi.si.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.fepi.si.model.Lancamento;

public class Lancamentos implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private EntityManager em;

	public Lancamentos(EntityManager em) {
		this.em = em;
	}
	
	public List<Lancamento> obterLancamentos(){
		TypedQuery<Lancamento> query = em.createQuery("FROM Lancamento", Lancamento.class);
		
		return query.getResultList();
	}
}
