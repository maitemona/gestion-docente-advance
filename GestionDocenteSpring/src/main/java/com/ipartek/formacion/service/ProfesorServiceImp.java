package com.ipartek.formacion.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ipartek.formacion.dbms.dao.interfaces.AlumnoDAO;
import com.ipartek.formacion.dbms.dao.interfaces.ProfesorDAO;
import com.ipartek.formacion.dbms.persistence.Profesor;
import com.ipartek.formacion.service.interfaces.ProfesorService;

@Service
public class ProfesorServiceImp implements ProfesorService {

	@Inject
	private ProfesorDAO profesorDao;
	
	
	@Override
	public Profesor create(Profesor profesor) {
		return profesorDao.create(profesor);
	}

	@Override
	public List<Profesor> getAll() {
		return profesorDao.getAll();
	}

	@Override
	public Profesor getById(int codigo) {
		return profesorDao.getById(codigo);
	}

	@Override
	public Profesor update(Profesor profesor) {
		return profesorDao.update(profesor);
	}

	@Override
	public void delete(int codigo) {
		profesorDao.delete(codigo);
		
	}

	@Override
	public void setProfesorDAO(ProfesorDAO profesorDao) {
		this.profesorDao=profesorDao;
		
	}

}
