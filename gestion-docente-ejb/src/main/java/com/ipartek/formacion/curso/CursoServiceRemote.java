package com.ipartek.formacion.curso;

import java.util.List;

import javax.ejb.Remote;


import com.ipartek.formacion.persistencia.Curso;



@Remote
public interface CursoServiceRemote {
	
	public Curso create(Curso curso);
	
	public List<Curso> getAll();
	
	public Curso getById(long codigo);
	
	public Curso update(Curso curso);

	public void delete(long codigo);

	public Curso verInforme(int codigo);
	
}
