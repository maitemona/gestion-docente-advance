package com.ipartek.formacion.curso;

import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ipartek.formacion.persistencia.Curso;

/**
 * Session Bean implementation class cursoServiceBean
 */
@Stateless(name = "CursoServiceBean")
public class CursoServiceBean implements CursoServiceRemote {

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Curso> getAll() {
		
		Query cursos = entityManager.createNamedQuery("curso.getAll");
		return cursos.getResultList();
	}

	@Override
	public Curso getById(long codigo) {
		Curso curso = entityManager.find(Curso.class, codigo);
		return curso;
	}

	@Override
	public Curso update(Curso curso) {
		entityManager.persist(curso);
		return curso;
	}

	@Override
	public void delete(long codigo) {
		// TODO Auto-generated method stub
		
	}
 

}
