package com.ipartek.formacion.service.interfaces;

import java.util.List;

import com.ipartek.formacion.alumno.AlumnoServiceRemote;
import com.ipartek.formacion.persistencia.Alumno;



public interface AlumnoServiceEJB {
	
	public void setAlumnoServiceRemote(AlumnoServiceRemote alumnoService);
	public Alumno getById(long codigo);
	public List<Alumno> getAll();
	public Alumno create(Alumno alumno);
	
	public Alumno update(Alumno alumno);
	
	public void delete(long codigo);

}
