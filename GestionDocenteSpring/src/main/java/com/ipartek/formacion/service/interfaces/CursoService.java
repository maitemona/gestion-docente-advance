package com.ipartek.formacion.service.interfaces;

import java.util.List;

import com.ipartek.formacion.curso.CursoServiceRemote;
import com.ipartek.formacion.persistencia.Curso;

public interface CursoService {

	public void setCursoServiceRemote(CursoServiceRemote cursoService);
	
	public Curso getById(long codigo);
	public List<Curso> getAll();

}
