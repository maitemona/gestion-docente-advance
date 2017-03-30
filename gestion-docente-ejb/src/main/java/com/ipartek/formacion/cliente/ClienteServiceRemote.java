package com.ipartek.formacion.cliente;

import java.util.List;

import javax.ejb.Remote;

import com.ipartek.formacion.persistencia.Cliente;


@Remote
public interface ClienteServiceRemote {

	public Cliente create(Cliente cliente);
	
	public List<Cliente> getAll();
	
	public Cliente getById(long codigo);
	
	public Cliente update(Cliente cliente);

	public void delete(long codigo);
}
