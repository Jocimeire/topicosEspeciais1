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

import br.fepi.si.financeiro.model.Lancamento;
import br.fepi.si.financeiro.model.Pessoa;
import br.fepi.si.financeiro.model.TipoLancamento;
import br.fepi.si.financeiro.repository.Lancamentos;
import br.fepi.si.financeiro.repository.Pessoas;
import br.fepi.si.financeiro.service.CadastroLancamentos;
import br.fepi.si.financeiro.service.CadastroPessoas;
import br.fepi.si.financeiro.service.NegocioException;
import br.fepi.si.financeiro.util.JpaUtil;

@ManagedBean
@ViewScoped
public class CadastroPessoasMBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pessoa pessoa = new Pessoa();
	private List<Pessoa> pessoas = new ArrayList<>();


	public void preparaCadastrados() {
		EntityManager em = JpaUtil.getEntityManager();
		try {
			this.pessoas = new Pessoas(em).obterPessoas();
		} finally {
			em.close();
		}

	}

	public void salvar() {
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			et.begin();
			CadastroPessoas cadastroPessoas = new CadastroPessoas(new Pessoas(em));
			cadastroPessoas.salvar(this.pessoa);
			this.pessoa = new Pessoa();
			context.addMessage(null, new FacesMessage("Lançamento salvo com sucesso!"));
			et.commit();
		} catch (NegocioException e) {
			et.rollback();
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		} finally {
			em.close();
		}

	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}