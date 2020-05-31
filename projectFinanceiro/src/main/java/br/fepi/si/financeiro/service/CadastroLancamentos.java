package br.fepi.si.financeiro.service;

import java.io.Serializable;
import java.util.Date;

import br.fepi.si.financeiro.model.Lancamento;
import br.fepi.si.financeiro.repository.Lancamentos;

public class CadastroLancamentos implements Serializable {

	private static final long serialVersionUID = 1L;

	private Lancamentos lancamentos;

	public CadastroLancamentos(Lancamentos lancamentos) {
		this.lancamentos = lancamentos;
	}

	public void salvar(Lancamento lancamento) throws NegocioException {
		if (lancamento.getDataPagamento() != null 
				&& lancamento.getDataPagamento().after(new Date()))
			throw new NegocioException("Data de pagamento não pode ser uma data futura.");
		this.lancamentos.guardar(lancamento);
	}
	
	public void excluir (Lancamento lancamento) throws NegocioException {
		lancamento = this.lancamentos.porId(lancamento.getIdLancamento());
		
		if (lancamento.getDataPagamento() != null)
			throw new NegocioException("Não é possivel remover lançamentos já pagos.");
		
		this.lancamentos.remover(lancamento);
	}

}