package com.ipartek.formacion.ws.curso;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ipartek.formacion.curso.CursoServiceRemote;
import com.ipartek.formacion.persistencia.Curso;

/**
 * Session Bean implementation class CursoServiceBeanSAOP
 */
@Stateless(name = "CursoServiceSOAP")
public class CursoServiceBeanSOAP implements CursoServiceBeanSOAPRemote {

	/*qiueremos un objeto xe tipo ejb==beans 
	 * (todos los soap son ejb), como el autowired pero para ejb*/
	@EJB
	CursoServiceRemote cS;
    /**
     * Default constructor. 
     */
    public CursoServiceBeanSOAP() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<Curso> getAll() {
		// TODO Auto-generated method stub
		return cS.getAll();
	}

}
