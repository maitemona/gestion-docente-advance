package com.ipartek.formacion.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import com.ipartek.formacion.persistencia.Profesor;
import com.ipartek.formacion.profesor.ProfesorServiceRemote;
import com.ipartek.formacion.service.interfaces.ProfesorServiceEJB;
@Service("profesorServiceEJB")
public class ProfesorServiceEJBImp implements ProfesorServiceEJB{

	@Resource(name = "profesorServiceRemote")//siempre se accede asi por si acaso
	private ProfesorServiceRemote profesorServiceRemote;
	
	@Override
	public Profesor getById(long codigo) {
		return profesorServiceRemote.getById(codigo);
	}

	@Override
	public List<Profesor> getAll() {
		return profesorServiceRemote.getAll();
	}

	@Override
	public Profesor create(Profesor profesor) {
		return profesorServiceRemote.create(profesor);
	}

	@Override
	public Profesor update(Profesor profesor) {
		return profesorServiceRemote.update(profesor);
	}

	@Override
	public void delete(long codigo) {
		Profesor profesor = profesorServiceRemote.getById(codigo);
		profesor.setActivo(false);
		profesorServiceRemote.update(profesor);
		
	}

	@Override
	public void setProfesorServiceRemote(ProfesorServiceRemote profesorService) {
		this.profesorServiceRemote = profesorService;
		
	}

}
