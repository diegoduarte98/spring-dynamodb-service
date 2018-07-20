package br.com.diegoduarte.repositories;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import br.com.diegoduarte.model.Pessoa;

@EnableScan
public interface PessoaRepository extends CrudRepository<Pessoa, String> {
	
	List<Pessoa> findByNome(String nome);
}
