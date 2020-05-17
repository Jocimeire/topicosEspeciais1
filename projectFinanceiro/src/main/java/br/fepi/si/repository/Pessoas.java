package br.fepi.si.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.fepi.si.model.Pessoa;

public class Pessoas implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private EntityManager em;

	public Pessoas(EntityManager em) {
		this.em = em;
	}
	
	public List<Pessoa> obterPessoas(){
		TypedQuery<Pessoa> query = em.createQuery("FROM Pessoa", Pessoa.class);
		
		return query.getResultList();
	}
}
