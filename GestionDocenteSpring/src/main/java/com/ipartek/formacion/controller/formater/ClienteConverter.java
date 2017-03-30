package com.ipartek.formacion.controller.formater;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.convert.converter.Converter;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import com.ipartek.formacion.persistencia.Cliente;
import com.ipartek.formacion.service.interfaces.ClienteServiceEJB;



public class ClienteConverter implements Converter<String, Cliente>{

	@Autowired
	ClienteServiceEJB cliS;

	@Override
	public Cliente convert(String arg0) {
		return cliS.getById(Long.parseLong((String) arg0));
	}




	

	


}
