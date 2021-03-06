package com.ipartek.formacion.curso;

import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;


import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import com.ipartek.formacion.persistencia.Curso;

/**
 * Session Bean implementation class cursoServiceBean
 */
@Stateless(name = "CursoServiceBean")
public class CursoServiceBean implements CursoServiceRemote {
	private static final Logger LOGGER = Logger.getLogger(CursoServiceBean.class);
    @PersistenceContext(unitName="gestiondocente")//nonbre definido en el perstince.xml del JPA
    private EntityManager entityManager;
  //va a hacer la factoria de objetos, me va gestionar todo
    
	/**
	 * Constructor
	 */
	    
    public CursoServiceBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Curso create(Curso curso) {
		curso = entityManager.merge(curso);
	
	//entityManager.persist(curso);
		return curso;
		//EntityTransaction txt = entityManager.getTransaction();
		//txt.begin();
		/*try{
			curso = entityManager.merge(curso);
			//txt.commit();
			
		}catch(Exception e){
			//txt.rollback();
		}
		//entityManager.persist(curso);
		return curso;*/
	}

	@Override
	public List<Curso> getAll() {
		
		TypedQuery<Curso> cursos = entityManager.createNamedQuery("curso.getAll", Curso.class);
		return cursos.getResultList();
		/*TypedQuery<Curso> pcursos = entityManager.createNamedQuery("curso.getAll");
		return pcursos.getResultList();*/
	}
	/*
	 * referenciamos un procedimiento almacenado con na anotacion
	 * 7spq.setParameter(1,curso.getCodigo());le paso la posicion del parametro
	 * @see com.ipartek.formacion.curso.CursoServiceRemote#getById(long)
	 */
	@Override
	public Curso getById(long codigo) {
		/*Curso curso = entityManager.find(Curso.class, codigo);
		StoredProcedureQuery spq= entityManager.createNamedStoredProcedureQuery("curso.getAlumnos");
		spq.setParameter(1,curso.getCodigo());
		//List<Alumno> alumnos = spq.getResultList();
		List<Alumno> alumnos = (List<Alumno>)spq.getResultList();
		curso.setAlumnos(alumnos);*/
		Curso curso = entityManager.find(Curso.class, codigo);
		/*
		 * StoredProcedureQuery spq =
		 * entityManager.createNamedStoredProcedureQuery("curso.getAlumnos");
		 * spq.setParameter(1, codigo); List<Alumno> alumnos = (List<Alumno>)
		 * spq.getResultList(); curso.setAlumnos(alumnos);
		 */
		return curso;
		//return curso;
	}

	@Override
	public Curso update(Curso curso) {
		entityManager.merge(curso);
		//EntityTransaction txt = entityManager.getTransaction();
		//txt.begin();
	/*	try{
		//	entityManager.persist(curso);
			entityManager.merge(curso);
			//txt.commit();
		}catch(Exception e){
			//txt.rollback();
		}*/
		//entityManager.persist(curso);
		return curso;
	}

	@Override
	public void delete(long codigo) {
		//EntityTransaction txt = entityManager.getTransaction();
		//txt.begin();
		try{
			entityManager.remove(entityManager.find(Curso.class, codigo));
			//txt.commit();
		}catch(Exception e){
			//txt.rollback();
		}
		//entityManager.persist(curso);
	//	return null;
		
	}

	@Override
	public Curso verInforme(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}
 

}
