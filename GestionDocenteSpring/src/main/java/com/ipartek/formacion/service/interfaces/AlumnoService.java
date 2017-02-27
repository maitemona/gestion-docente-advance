package com.ipartek.formacion.service.interfaces;
/**
 * definimos los metodos de la capa logica q de momento van a ser los mismo q la de la capa DAO.
 * 
 */
import java.util.List;

import com.ipartek.formacion.dbms.dao.interfaces.AlumnoDAO;
import com.ipartek.formacion.dbms.persistence.Alumno;

public interface AlumnoService {

	public Alumno create(Alumno alumno);
	
	public List<Alumno> getAll();
	
	public Alumno getById(int codigo);
	
	public Alumno update(Alumno alumno);

	public void delete(int codigo);
	
	public Alumno getByDni(String dni);
	
	public void setAlumnoDAO(AlumnoDAO alumnoDao);
	public Alumno getInforme(int codigo);
}
