package com.marcelo.infraestructure.components;

import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

import org.bson.codecs.jsr310.LocalTimeCodec;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcelo.domain.dtos.EmailDto;
import com.marcelo.domain.entities.LogMensagem;
import com.marcelo.infraestructure.repositories.LogMensagemRepository;

@Component
public class EmailConsumerComponent {
	
	@Autowired
	private EmailComponent emailComponent;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private LogMensagemRepository logMensagemRepository;
	
	
	@RabbitListener(queues = { "${queue.name}" })
	public void proccessMessage(@Payload String message) {
		
		LogMensagem logMensagem = new LogMensagem();
		logMensagem.setId(UUID.randomUUID());
		logMensagem.setMensagem(message);
		logMensagem.setDataHora(LocalTime.now());		
		
		try {
			EmailDto dto = objectMapper.readValue(message, EmailDto.class);			
			emailComponent.send(dto);	
			
			logMensagem.setStatus("SUCESSO");			
			
		}catch (Exception e) {
			logMensagem.setStatus("ERRO");
			logMensagem.setErro(e.getMessage());
			
		}finally {
			logMensagemRepository.save(logMensagem);
		}		
	}
}






