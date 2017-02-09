package com.ipartek.formacion.service.interfaces;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;


import com.ipartek.formacion.dbms.dao.interfaces.ProfesorDAO;

import com.ipartek.formacion.dbms.persistence.Profesor;



@Service
public interface ProfesorService {

	public Profesor create(Profesor profesor);
	
	public List<Profesor> getAll();
	
	public Profesor getById(int codigo);
	
	public Profesor update(Profesor profesor);

	public void delete(int codigo);
	
	public void setProfesorDAO(ProfesorDAO profesorDao);


	
}
