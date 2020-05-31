package br.fepi.si.financeiro.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.fepi.si.financeiro.model.Lancamento;

public class Lancamentos implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager em;

	public Lancamentos(EntityManager em) {
		this.em = em;
	}

	public Lancamento porId (Long id) {
		return em.find(Lancamento.class, id);		
	}
	
	public void remover (Lancamento lancamento) {
		this.em.remove(lancamento);
	}
	
	public List<String> descricoesQueContem(String descricao) {
		TypedQuery<String> query = em.createQuery(
				"SELECT DISTINCT descricao FROM Lancamento " 
				+ "WHERE upper(descricao) LIKE upper(:descricao)",
				String.class);
		query.setParameter("descricao", "%" + descricao + "%");
		return query.getResultList();
	}

	public void adicionar(Lancamento lancamento) {
		this.em.persist(lancamento);
	}
	
	public void guardar (Lancamento lancamento) {
		this.em.merge(lancamento);
	}

	public List<Lancamento> obterLancamentos() {
		TypedQuery<Lancamento> query = em.createQuery("FROM Lancamento", Lancamento.class);
		return query.getResultList();
	}

}