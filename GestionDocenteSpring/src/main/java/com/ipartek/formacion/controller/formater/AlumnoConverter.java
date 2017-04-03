package com.ipartek.formacion.controller.formater;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.ipartek.formacion.persistencia.Alumno;
import com.ipartek.formacion.service.interfaces.AlumnoServiceEJB;



public class AlumnoConverter implements Converter<String,Alumno>{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AlumnoConverter.class);

	@Autowired
	AlumnoServiceEJB aS;
	@Override
	public Alumno convert(String arg0) {
		LOGGER.info(arg0.toString());
		return aS.getById(Long.parseLong((String) arg0));
	}

}
