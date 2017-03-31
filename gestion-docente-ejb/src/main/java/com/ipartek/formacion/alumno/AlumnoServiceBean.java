package com.ipartek.formacion.alumno;

import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.ipartek.formacion.persistencia.Alumno;


/**
 * Session Bean implementation class AlumnoServiceBean
 */
@Stateless(name = "alumnoServiceBean")
public class AlumnoServiceBean implements AlumnoServiceRemote {

	@PersistenceContext(unitName="gestiondocente")//nonbre definido en el perstince.xml del JPA
	 private EntityManager entityManager;
	@Override
	public Alumno create(Alumno alumno) {
		try{
			entityManager.persist(alumno);
		entityManager.flush();
	}catch(Exception e){
		
	}

	return alumno;
	}

	@Override
	public List<Alumno> getAll() {
		TypedQuery<Alumno> alumnos = entityManager.createNamedQuery("alumno.getAll", Alumno.class);
		return alumnos.getResultList();
	}

	@Override
	public Alumno getById(long codigo) {
		Alumno alumno = entityManager.find(Alumno.class, codigo);
	
		return alumno;
	}

	@Override
	public Alumno update(Alumno alumno) {
		try{
			
			entityManager.merge(alumno);
				
			}catch(Exception e){
				
			}
			
			return alumno;
	}

	@Override
	public void delete(long codigo) {
		try {
			entityManager.remove(entityManager.find(Alumno.class, codigo));
			// tx.commit();
		} catch (Exception e) {
			// tx.rollback();
		}
		
	}

}
