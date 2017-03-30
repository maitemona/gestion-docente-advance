package com.ipartek.formacion.service.interfaces;

import java.util.List;


import com.ipartek.formacion.persistencia.Profesor;
import com.ipartek.formacion.profesor.ProfesorServiceRemote;


public interface ProfesorServiceEJB {
	
	public void setProfesorServiceRemote(ProfesorServiceRemote profesorService);
	public Profesor getById(long codigo);
	public List<Profesor> getAll();
	public Profesor create(Profesor profesor);
	
	public Profesor update(Profesor profesor);
	
	public void delete(long codigo);


}
