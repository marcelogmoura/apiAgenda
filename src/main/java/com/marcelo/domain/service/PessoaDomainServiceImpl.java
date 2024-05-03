package com.marcelo.domain.service;

import java.util.Date;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcelo.domain.dtos.AutenticarPessoaRequestDto;
import com.marcelo.domain.dtos.AutenticarPessoaResponseDto;
import com.marcelo.domain.dtos.CriarPessoaRequestDto;
import com.marcelo.domain.dtos.CriarPessoaResponseDto;
import com.marcelo.domain.dtos.EmailDto;
import com.marcelo.domain.entities.Pessoa;
import com.marcelo.domain.exceptions.AcessoNegadoException;
import com.marcelo.domain.exceptions.EmailJaCadastradoException;
import com.marcelo.domain.interfaces.PessoaDomainService;
import com.marcelo.infraestructure.components.CryptoSHA256Component;
import com.marcelo.infraestructure.components.EmailComponent;
import com.marcelo.infraestructure.components.EmailProducerComponent;
import com.marcelo.infraestructure.components.TokenComponent;
import com.marcelo.infraestructure.repositories.PessoaRepository;

@Service
public class PessoaDomainServiceImpl implements PessoaDomainService{
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private CryptoSHA256Component cryptoSHA256Component;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private EmailProducerComponent emailProducerComponent;
	
	@Autowired
	private TokenComponent tokenComponent;

	@Override
	public CriarPessoaResponseDto criar(CriarPessoaRequestDto dto) {
		
		if(pessoaRepository.findByEmail(dto.getEmail()) != null)
			throw new EmailJaCadastradoException(); 
		
		Pessoa pessoa = new Pessoa();
		
		pessoa.setId(UUID.randomUUID());
		pessoa.setNome(dto.getNome());
		pessoa.setEmail(dto.getEmail());
		pessoa.setSenha(cryptoSHA256Component.encrypt(dto.getSenha()));
		
		pessoaRepository.save(pessoa);
		
		enviarEmailBoasVindas(pessoa);
		
		CriarPessoaResponseDto response = modelMapper.map(pessoa, CriarPessoaResponseDto.class);
		response.setDataHoraCadastro(new Date());
			
		return response;
	}
	
	
	@Override
	public AutenticarPessoaResponseDto autenticar(AutenticarPessoaRequestDto dto) {
		
		Pessoa pessoa = pessoaRepository.findEmailAndSenha(dto.getEmailAcesso(), cryptoSHA256Component.encrypt(dto.getSenhaAcesso()));
		
		if(pessoa == null)
			throw new AcessoNegadoException();
		
		AutenticarPessoaResponseDto response = modelMapper.map(pessoa, AutenticarPessoaResponseDto.class);
		response.setAccessToken(tokenComponent.generateToken(pessoa.getId()));
				
		return response;
	}
	
	
	private void enviarEmailBoasVindas(Pessoa pessoa) {
		
		String to = pessoa.getEmail();
		String subject = "Bem vindo ao sistema ";
		String body = "Olá, " + pessoa.getNome()
							  +"\nConta criada com sucesso"
							  +"\nSeja Bem Vindo !!"
							  +"\nAtt, "
							  +"\nEquipe Dev";
		
		EmailDto dto = new EmailDto();
		dto.setDestinatario(to);
		dto.setAssunto(subject);
		dto.setMensagem(body);
		
		try {
			emailProducerComponent.sendMessage(dto);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


	

}





