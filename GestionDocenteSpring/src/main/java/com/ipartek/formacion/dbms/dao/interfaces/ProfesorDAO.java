package com.ipartek.formacion.dbms.dao.interfaces;

import java.util.List;


import com.ipartek.formacion.dbms.persistence.Profesor;
/**
 * ESta interfaz define los metodos de consulta a una BBDD de la entidad Profesor
 * que tiene su correspondecia en la clsee <code>Profesor</code>
 * de la capa persistencia
 * opreciones de CRUD
 * @author Mai
 *
 */
public interface ProfesorDAO extends DAOSetter{
	
	/**
	 * Metodo para crear un objeto <code>Profesor</code> en la BBDD.
	 * El <code>Profesor</code> tendra los datos necesarios excepto el codigo que se genera por la BBDD
	 * @param profesor
	 * @return <code>Profesor</code> le devuelve el objeto enviado con el codigo generado en la BBDD
	 */
	public Profesor create(Profesor profesor);
	
	public List<Profesor> getAll();
	
	public Profesor getById(int codigo);
	
	public Profesor update(Profesor profesor);

	public void delete(int codigo);

}
