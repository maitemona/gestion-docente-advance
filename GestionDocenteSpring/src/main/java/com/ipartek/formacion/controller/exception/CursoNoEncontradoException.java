package com.ipartek.formacion.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Curso no encontrado")//404
public class CursoNoEncontradoException extends Exception{
	
	private static final long serialVersionUID=1;
	
	public CursoNoEncontradoException(long codigo){
		super("CursoNoEncontradoException:" + codigo);
	}
			
	

}
