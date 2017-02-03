package com.ipartek.formacion.dbms.dao.interfaces;

import java.util.List;

import com.ipartek.formacion.dbms.persistence.Alumno;

/**
 * ESta interfaz define los metodos de consulta a una BBDD de la entidad Alumno
 * que tiene su correspondecia en la calse <code>Alumno</code>
 * de la capa persistencia
 * opreciones de CRUD
 * @author Mai
 *
 */
public interface AlumnoDAO extends DAOSetter{
	/**
	 * Metodo para crear un objeto <code>Alumno</code> en la BBDD.
	 * El <code>Alumno</code> tendra los datos necesarios excepto el codigo que se genera por la BBDD
	 * @param alumno
	 * @return <code>Alumno</code> le devuelve el obejto enviado con el codigo generado en la BBDD
	 */
	public Alumno create(Alumno alumno);
	
	public List<Alumno> getAll();
	
	public Alumno getById(int codigo);
	
	public Alumno update(Alumno alumno);

	public void delete(int codigo);
}
