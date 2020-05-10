package br.fepi.si.model;

public enum TipoLancamentoEnum {	
	
	RECEITA("Receita"), DESPESA("Despesa");

	private String descricao;

	
	TipoLancamentoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
