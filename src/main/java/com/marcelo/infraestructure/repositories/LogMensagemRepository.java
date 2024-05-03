package com.marcelo.infraestructure.repositories;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.marcelo.domain.entities.LogMensagem;

@Repository
public interface LogMensagemRepository extends MongoRepository<LogMensagem, UUID>{

}
