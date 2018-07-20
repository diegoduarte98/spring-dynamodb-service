package br.com.diegoduarte.repositories;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import br.com.diegoduarte.model.Usuario;

@EnableScan
public interface UserRepository extends CrudRepository<Usuario, String> {

	List<Usuario> findByLastName(String lastName);
}
