package com.marcelo.infraestructure.components;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcelo.domain.dtos.EmailDto;

@Component
public class EmailProducerComponent {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private Queue queue;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	
	public void sendMessage(EmailDto dto) throws Exception {
		
		// serializando dados dto para JSON
		String data = objectMapper.writeValueAsString(dto);
		
		rabbitTemplate.convertAndSend(queue.getName(), data);
	}
}
