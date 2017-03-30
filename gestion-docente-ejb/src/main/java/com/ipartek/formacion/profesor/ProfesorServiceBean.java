package com.ipartek.formacion.profesor;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.persistence.TypedQuery;


import com.ipartek.formacion.persistencia.Profesor;


/**
 * Session Bean implementation class ProfesorServiceRemote
 */
@Stateless(name = "profesorServiceBean")
public class ProfesorServiceBean implements ProfesorServiceRemote{

	
	 @PersistenceContext(unitName="gestiondocente")//nonbre definido en el perstince.xml del JPA
	 private EntityManager entityManager;
	@Override
	public Profesor create(Profesor profesor) {
		try{
			entityManager.persist(profesor);
			entityManager.flush();
		}catch(Exception e){
			
		}
	
		return profesor;
	}

	@Override
	public List<Profesor> getAll() {
		//Query profesores = entityManager.createNamedQuery("profesor.getAll");
		//return profesores.getResultList();
		TypedQuery<Profesor> profesores = entityManager.createNamedQuery("profesor.getAll", Profesor.class);
	
		return profesores.getResultList();
		
	}

	@Override
	public Profesor getById(long codigo) {
		Profesor profesor = entityManager.find(Profesor.class, codigo);
		//StoredProcedureQuery spq= entityManager.createNamedStoredProcedureQuery("curso.getProfesor");
		//spq.setParameter(1,profesor.getCodigo());
		//List<Profesor> profesores = spq.getResultList();
		//profesor.se;
		return profesor;
	}

	@Override
	public Profesor update(Profesor profesor) {
		try{
			
			entityManager.merge(profesor);
				
			}catch(Exception e){
				
			}
			
			return profesor;
	}

	@Override
	public void delete(long codigo) {
		try{
			entityManager.remove(entityManager.find(Profesor.class, codigo));
			//txt.commit();
		}catch(Exception e){
			//txt.rollback();
		}
	
		
	}

}
