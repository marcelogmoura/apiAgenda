package com.marcelo.domain.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AutenticarPessoaRequestDto {
	
	@NotEmpty(message = "Informe o e-mail de acesso")
	@Email(message = "e-mail precisa ser válido")
	private String emailAcesso;
	
	@Size(min = 4, message = "senha muito curta")
	@NotEmpty(message = "Informe a senha de acesso")
	private String senhaAcesso; 

}
