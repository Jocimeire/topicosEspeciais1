package br.fepi.si.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.fepi.si.model.Lancamento;
import br.fepi.si.model.Pessoa;
import br.fepi.si.repository.Pessoas;
import br.fepi.si.util.JpaUtil;

@ManagedBean
@ViewScoped
public class ConsultaPessoasMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private EntityManager em;
	
	private List<Pessoa> pessoas = new ArrayList<>();
	
	public void consultar() {
		EntityManager em = JpaUtil.getEntityManager();
		this.pessoas = new Pessoas(em).obterPessoas();
		em.close();
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
}
