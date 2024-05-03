package com.marcelo.infraestructure.repositories;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.marcelo.domain.entities.Pessoa;

public interface PessoaRepository extends MongoRepository<Pessoa, UUID>{
	
	@Query("{ 'email' : ?0 }")
	Pessoa findByEmail(String email);
	
	@Query("{ 'email' : ?0, 'senha' : ?1 }")
	Pessoa findEmailAndSenha(String email, String senha);

}
