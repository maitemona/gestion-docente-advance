package com.ipartek.formacion.controller.resolver;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class MyMappingExceptionResolver extends SimpleMappingExceptionResolver {

	@Override
	protected String buildLogMessage(Exception ex, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return "Gestion Docente esception:" +ex.getLocalizedMessage();
	}
	
	

}
