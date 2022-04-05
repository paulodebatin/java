package br.com.crud.controller;

import javax.enterprise.context.ApplicationScoped;

import br.com.crud.entity.Produto;

@ApplicationScoped
public class ProdutoController {

    public Produto update(Long id, Produto produto) {
        Produto produtoEntity = carregaProduto(id);
        
        if  (produtoEntity == null)
        	return null;

        if  (produto.getNome() != null) {
        	produtoEntity.setNome(produto.getNome());
        }
        
        if  (produto.getPreco() != null) {
            produtoEntity.setPreco(produto.getPreco());
        }
        
        if  (produto.getQuantidade() != null) {
        	produtoEntity.setQuantidade(produto.getQuantidade());
        }	

        return produtoEntity;
    }

	public Produto carregaProduto(Long id) {
		Produto produtoEntity = Produto.findById(id);

        if (produtoEntity == null) {
            return null;
        }
		return produtoEntity;
	}
}
