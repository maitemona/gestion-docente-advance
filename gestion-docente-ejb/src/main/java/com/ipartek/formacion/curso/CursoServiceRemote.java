package com.ipartek.formacion.curso;

import java.util.List;

import javax.ejb.Remote;

import com.ipartek.formacion.persistencia.Curso;



@Remote
public interface CursoServiceRemote {
	
	public Curso create(Curso curso);
	
	public List<Curso> getAll();
	
	public Curso getById(int codigo);
	
	public Curso update(Curso curso);

	public void delete(int codigo);
	
	/*public Curso getByDni(String dni);
	
	public void setAlumnoDAO(AlumnoDAO alumnoDao);
	public Alumno getInforme(int codigo);
*/
}
