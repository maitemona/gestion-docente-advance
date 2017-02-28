package com.ipartek.formacion.curso;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ipartek.formacion.persistencia.Curso;

/**
 * Session Bean implementation class cursoServiceBean
 */
@Stateless(name = "CursoServiceBean")
public class CursoServiceBean implements CursoServiceRemote {

    @PersistenceContext(unitName="gestiondocente")
    private EntityManager entityManager;
 
    
    
    
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Curso getById(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Curso update(Curso curso) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int codigo) {
		// TODO Auto-generated method stub
		
	}
 

}
