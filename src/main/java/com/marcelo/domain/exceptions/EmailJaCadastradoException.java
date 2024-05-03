package com.marcelo.domain.exceptions;

public class EmailJaCadastradoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EmailJaCadastradoException() {
		super("e-mail já cadastrado");
	}

}
