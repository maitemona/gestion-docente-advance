package com.ipartek.formacion.cliente;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.ipartek.formacion.persistencia.Cliente;
import com.ipartek.formacion.persistencia.Profesor;


@Stateless(name = "clienteServiceBean")
public class ClienteServiceBean implements ClienteServiceRemote{

	@PersistenceContext(unitName="gestiondocente")//nonbre definido en el perstince.xml del JPA
	 private EntityManager entityManager;
	@Override
	public Cliente create(Cliente cliente) {
		try{
			entityManager.persist(cliente);
			entityManager.flush();
		}catch(Exception e){
			
		}
	
		return cliente;
	}

	@Override
	public List<Cliente> getAll() {
		TypedQuery<Cliente> clientes = entityManager.createNamedQuery("cliente.getAll", Cliente.class);
		return clientes.getResultList();
	}

	@Override
	public Cliente getById(long codigo) {
		Cliente cliente = entityManager.find(Cliente.class, codigo);
		//StoredProcedureQuery spq= entityManager.createNamedStoredProcedureQuery("curso.getProfesor");
		//spq.setParameter(1,profesor.getCodigo());
		//List<Profesor> profesores = spq.getResultList();
		//profesor.se;
		return cliente;
	}

	@Override
	public Cliente update(Cliente cliente) {
		try{
			
			entityManager.merge(cliente);
				
			}catch(Exception e){
				
			}
			
			return cliente;
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
