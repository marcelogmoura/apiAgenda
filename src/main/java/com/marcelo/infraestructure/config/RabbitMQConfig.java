package com.marcelo.infraestructure.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
	

	@Value("${queue.name}")   // @ value para ler conteudo do application properties
	private String queueName;
	
	@Bean
	public Queue queue() {
		return new Queue(queueName, true);
	}

}
