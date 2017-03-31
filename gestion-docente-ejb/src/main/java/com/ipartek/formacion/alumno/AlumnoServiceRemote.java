package com.ipartek.formacion.alumno;

import java.util.List;

import javax.ejb.Remote;

import com.ipartek.formacion.persistencia.Alumno;



@Remote
public interface AlumnoServiceRemote {

	public Alumno create(Alumno alumno);
	
	public List<Alumno> getAll();
	
	public Alumno getById(long codigo);
	
	public Alumno update(Alumno alumno);

	public void delete(long codigo);
}
