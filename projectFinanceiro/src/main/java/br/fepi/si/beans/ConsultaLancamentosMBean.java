package br.fepi.si.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.fepi.si.model.Lancamento;
import br.fepi.si.repository.Lancamentos;
import br.fepi.si.util.JPAUtil;;

@ManagedBean
@ViewScoped
public class ConsultaLancamentosMBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<Lancamento> Lancamentos = new ArrayList<>();
	
	public void consultar(){
		EntityManager em = JPAUtil.getEntityManager();
		this.Lancamentos = new Lancamentos(em).obterLancamentos();
		em.close();
	}

	public List<Lancamento> getLancamentos() {
		return Lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		Lancamentos = lancamentos;
	}
	
	
	
}
