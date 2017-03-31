package com.ipartek.formacion.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ipartek.formacion.alumno.AlumnoServiceRemote;

import com.ipartek.formacion.persistencia.Alumno;
import com.ipartek.formacion.persistencia.Cliente;
import com.ipartek.formacion.service.interfaces.AlumnoServiceEJB;

@Service("alumnoServiceEJB")
public class AlumnoServiceEJBImp implements AlumnoServiceEJB{

	
	
	@Resource(name = "alumnoServiceRemote")//siempre se accede asi por si acaso
	private AlumnoServiceRemote alumnoServiceRemote;
	
	@Override
	public void setAlumnoServiceRemote(AlumnoServiceRemote alumnoService) {
		this.alumnoServiceRemote = alumnoService;
		
	}

	@Override
	public Alumno getById(long codigo) {
		return alumnoServiceRemote.getById(codigo);
	}

	@Override
	public List<Alumno> getAll() {
		return alumnoServiceRemote.getAll();
	}

	@Override
	public Alumno create(Alumno alumno) {
		return alumnoServiceRemote.update(alumno);
	}

	@Override
	public Alumno update(Alumno alumno) {
		return alumnoServiceRemote.update(alumno);
	}

	@Override
	public void delete(long codigo) {
		Alumno alumno = alumnoServiceRemote.getById(codigo);
		alumno.setActivo(false);
		alumnoServiceRemote.update(alumno);
		
	}

}
