package com.crud.vendas.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.vendas.domain.model.Pessoa;
import com.crud.vendas.domain.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	List<Pessoa> findByNome(String nome);
	List<Pessoa> findByNomeContaining(String nome);
	
}
