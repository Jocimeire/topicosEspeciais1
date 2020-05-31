package br.fepi.si.financeiro.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.fepi.si.financeiro.model.Lancamento;
import br.fepi.si.financeiro.model.Pessoa;

public class Pessoas implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager em;

	public Pessoas(EntityManager em) {		
		this.em = em;
	}	
	
	public Pessoa porId(Long id) {
		return em.find(Pessoa.class, id);
	}

	public void guardar (Pessoa pessoa) {
		this.em.merge(pessoa);
	}
	
	public void adicionar(Pessoa pessoa) {
		this.em.persist(pessoa);
	}
	
	public List<Pessoa> obterPessoas(){
		TypedQuery<Pessoa> query = 
				em.createQuery("from Pessoa", Pessoa.class);
		return query.getResultList();
	}
	
	public void remover (Pessoa pessoa) {
		this.em.remove(pessoa);
	}
}