package com.crud.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.crud.domain.model.Pessoa;
import com.crud.domain.repository.PessoaRepository;
import com.crud.domain.service.CadastroPessoaService;

@CrossOrigin
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private CadastroPessoaService cadastroPessoa;
	
	@GetMapping
	public List<Pessoa> listar() {
		return pessoaRepository.findAll();
	}
	
	@GetMapping("/{pessoaId}")
	public ResponseEntity<Pessoa> buscar(@PathVariable Long pessoaId) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(pessoaId);
		
		if (pessoa.isPresent()) {
			return ResponseEntity.ok(pessoa.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pessoa adicionar(@Valid @RequestBody Pessoa pessoa) {
		return cadastroPessoa.salvar(pessoa);
	}
	
	@PutMapping("/{pessoaId}")
	public ResponseEntity<Pessoa> atualizar(@Valid @PathVariable Long pessoaId,
			@RequestBody Pessoa pessoa) {
		
		if (!pessoaRepository.existsById(pessoaId)) {
			return ResponseEntity.notFound().build();
		}
		
		pessoa.setId(pessoaId);
		pessoa = cadastroPessoa.salvar(pessoa);
		
		return ResponseEntity.ok(pessoa);
	}
	
	@DeleteMapping("/{pessoaId}")
	public ResponseEntity<Void> remover(@PathVariable Long pessoaId) {
		if (!pessoaRepository.existsById(pessoaId)) {
			return ResponseEntity.notFound().build();
		}
		
		cadastroPessoa.excluir(pessoaId);
		
		return ResponseEntity.noContent().build();
	}
	
}
