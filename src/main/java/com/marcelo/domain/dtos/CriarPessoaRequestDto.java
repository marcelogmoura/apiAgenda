package com.marcelo.domain.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CriarPessoaRequestDto {
	
	@NotEmpty(message = "NOME de 3 a 20 caracteres")
	@Size(min = 3, max = 20, message = "Informe o NOME")
	private String nome;
	
	@Email(message = "Informe um email válido")
	@NotEmpty(message = "preencha o campo EMAIL")
	private String email;
	
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{3,20}$",
			message = "Por favor, informe a senha com letras maiúsculas, minúsculas, números, símbolos e pelo menos 3 caracteres.")
	@NotEmpty(message = "preencha o campo SENHA")
	private String senha;

}
