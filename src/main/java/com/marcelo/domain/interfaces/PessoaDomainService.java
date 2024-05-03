package com.marcelo.domain.interfaces;

import com.marcelo.domain.dtos.AutenticarPessoaRequestDto;
import com.marcelo.domain.dtos.AutenticarPessoaResponseDto;
import com.marcelo.domain.dtos.CriarPessoaRequestDto;
import com.marcelo.domain.dtos.CriarPessoaResponseDto;


public interface PessoaDomainService {
	
	CriarPessoaResponseDto criar(CriarPessoaRequestDto dto);
	
	AutenticarPessoaResponseDto autenticar(AutenticarPessoaRequestDto dto);

}
