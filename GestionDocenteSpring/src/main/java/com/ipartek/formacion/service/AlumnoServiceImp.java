package com.ipartek.formacion.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ipartek.formacion.dbms.dao.interfaces.AlumnoDAO;
import com.ipartek.formacion.service.interfaces.AlumnoService;

@Service
public class AlumnoServiceImp implements AlumnoService {
	
	@Inject
	private AlumnoDAO alumnosDao;

}
