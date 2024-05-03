package com.marcelo.infraestructure.repositories;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.marcelo.domain.entities.Tarefa;

public interface TarefaRepository extends MongoRepository<Tarefa, UUID>{

}
