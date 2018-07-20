package br.com.diegoduarte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diegoduarte.model.Usuario;
import br.com.diegoduarte.repositories.UserRepository;

@RestController
public class UsuarioController {
	
	@Autowired
	private UserRepository repository;
	
	@RequestMapping("/findall")
	public Iterable<Usuario> findAll() {
		return repository.findAll();
	}
}
