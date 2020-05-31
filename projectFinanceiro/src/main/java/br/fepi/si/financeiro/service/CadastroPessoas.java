package br.fepi.si.financeiro.service;

import java.io.Serializable;
import java.util.Date;

import br.fepi.si.financeiro.model.Lancamento;
import br.fepi.si.financeiro.model.Pessoa;
import br.fepi.si.financeiro.repository.Lancamentos;
import br.fepi.si.financeiro.repository.Pessoas;

public class CadastroPessoas implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pessoas pessoas;

	public CadastroPessoas(Pessoas pessoas) {
		this.pessoas = pessoas;
	}

	public void salvar(Pessoa pessoa) throws NegocioException {
		if (pessoa.getNome() == null)
			throw new NegocioException("Nome não pode ser vazio!");
		this.pessoas.guardar(pessoa);
	}
	
	public void excluir (Pessoa pessoa) throws NegocioException {
		pessoa = this.pessoas.porId(pessoa.getIdPessoa());
		
		this.pessoas.remover(pessoa);
	}

}