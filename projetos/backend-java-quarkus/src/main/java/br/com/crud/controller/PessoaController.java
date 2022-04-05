package br.com.crud.controller;

import javax.enterprise.context.ApplicationScoped;

import br.com.crud.entity.Pessoa;

@ApplicationScoped
public class PessoaController {

    public Pessoa update(Long id, Pessoa pessoa) {
        Pessoa pessoaEntity = carregaPessoa(id);
        if  (pessoa == null)
        	return null;

        if  (pessoa.getNome() != null) {
            pessoaEntity.setNome(pessoa.getNome());
        }
        if  (pessoa.getEndereco() != null) {
        	pessoaEntity.setEndereco(pessoa.getEndereco());
        }
        if  (pessoa.getNumero() != null) {
        	pessoaEntity.setNumero(pessoa.getNumero());
        }
        if  (pessoa.getBairro() != null) {
        	pessoaEntity.setBairro(pessoa.getBairro());
        }
        if  (pessoa.getCidade() != null) {
        	pessoaEntity.setCidade(pessoa.getCidade());
        }
        if  (pessoa.getUf() != null) {
        	pessoaEntity.setUf(pessoa.getUf());
        }
        if  (pessoa.getCep() != null) {
        	pessoaEntity.setCep(pessoa.getCep());
        }
        if  (pessoa.getFumante() != null) {
        	pessoaEntity.setFumante(pessoa.getFumante());
        }
        if  (pessoa.getReceberNoticias() != null) {
        	pessoaEntity.setReceberNoticias(pessoa.getReceberNoticias());
        }
        if  (pessoa.getDataNascimento() != null) {
        	pessoaEntity.setDataNascimento(pessoa.getDataNascimento());
        }

        return pessoaEntity;
    }

	public Pessoa carregaPessoa(Long id) {
		Pessoa pessoaEntity = Pessoa.findById(id);

        if (pessoaEntity == null) {
            return null;
        }
		return pessoaEntity;
	}
}
