package br.com.diegoduarte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.diegoduarte.model.Pessoa;
import br.com.diegoduarte.repositories.PessoaRepository;

@RestController
public class PessoaController {
	
	@Autowired
	private PessoaRepository repository;
	
	@GetMapping("/pessoas")
	public Iterable<Pessoa> findAll() {
		return repository.findAll();
	}
	
	@PostMapping("/pessoas")
	public Pessoa save(@RequestBody Pessoa pessoa) {
		return repository.save(pessoa);
	}
}
