package com.marcelo.domain.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class AutenticarPessoaResponseDto {
	
	private UUID id;
	private String nome;
	private String email;
	private String accessToken;	

}
