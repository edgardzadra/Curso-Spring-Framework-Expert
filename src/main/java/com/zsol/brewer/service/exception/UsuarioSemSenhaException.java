package com.zsol.brewer.service.exception;

public class UsuarioSemSenhaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UsuarioSemSenhaException(String message) {
		super(message);
	}

}
