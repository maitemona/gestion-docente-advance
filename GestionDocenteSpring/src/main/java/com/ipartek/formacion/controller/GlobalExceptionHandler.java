/**
 * excepcion que va acontrola, y gestionar las excepciones a nivel del todo el proyecto
*/
package com.ipartek.formacion.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
/*
 *2º metodo, a nivel de bbdd
 * 
 * */
	@ExceptionHandler(SQLException.class)
	public String handlerSQLException(HttpServletRequest request, Exception ex){
	
		logger.error("SQLException URL:" + request.getRequestURL());
		logger.error("Mensaje: "+ex.getMessage());
		
		return "database_error";
	
	}
	
	/**
	 * 3 metodo mas abstracto
	 * Esta se gestionara a traves del contexto de spring
	 * Es una capa mas alta de abstraccion..necesitamos crear una paquete que me lo gestione
	 * dentro habra una clase que extienda de extends SimpleMappingExceptionResolver
	 * 
	 * Tengo que meter un bean en servlet context , para mandarla a una vista generic_error.
	 * 
	 * y alli le dijo que class me esta resolviendo este metodo: (MAPEO) class="com.ipartek.formacion.controller.resolver.MyMappingExceptionResolver"
	 * */
	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason ="IOException lanzada")
	@ExceptionHandler(IOException.class)
	public void handlerIOException(){
		logger.error("Se ha ejecutado una exception de tipo IOException");
	}
}
