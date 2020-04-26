package br.fepi.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.fepi.model.Produto;

@ManagedBean
@ViewScoped
public class ListaComprasMBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Produto produto = new Produto();
	
	public void inserir() {
		this.produtos.add(produto);
		this.produto = new Produto();
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}
	
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	private List<Produto> produtos = new ArrayList();
}
