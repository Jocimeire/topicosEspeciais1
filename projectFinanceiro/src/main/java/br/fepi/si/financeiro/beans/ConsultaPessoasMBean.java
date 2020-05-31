package br.fepi.si.financeiro.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.fepi.si.financeiro.model.Pessoa;
import br.fepi.si.financeiro.repository.Pessoas;
import br.fepi.si.financeiro.service.CadastroPessoas;
import br.fepi.si.financeiro.util.JpaUtil;

@ManagedBean
@ViewScoped
public class ConsultaPessoasMBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager em;

	private List<Pessoa> pessoas = new ArrayList<>();
	private Pessoa pessoaSelecionada = new Pessoa();
	
	public ConsultaPessoasMBean() {
	}

	public void consultar() {
		EntityManager em = JpaUtil.getEntityManager();
		this.pessoas = new Pessoas(em).obterPessoas();
		em.close();
	}

	public void excluir() {
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();

		CadastroPessoas cadastroPessoas = new CadastroPessoas(new Pessoas(em));

		FacesContext context = FacesContext.getCurrentInstance();

		try {
			et.begin();
			cadastroPessoas.excluir(pessoaSelecionada);
			context.addMessage(null,
					new FacesMessage("Pessoa #" + pessoaSelecionada.getIdPessoa() + " removido com sucesso."));
			et.commit();
			this.consultar();
		} catch (Exception e) {
			et.rollback();
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_FATAL);
			context.addMessage(null, msg);
		} finally {
			em.close();
		}
	}

	public Pessoa getPessoaSelecionada() {
		return pessoaSelecionada;
	}
	
	public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
		this.pessoaSelecionada = pessoaSelecionada;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
}
