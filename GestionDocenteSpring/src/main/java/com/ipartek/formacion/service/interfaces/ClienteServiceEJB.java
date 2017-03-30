package com.ipartek.formacion.service.interfaces;

import java.util.List;

import com.ipartek.formacion.cliente.ClienteServiceRemote;
import com.ipartek.formacion.persistencia.Cliente;



public interface ClienteServiceEJB {

	public void setClienteServiceRemote(ClienteServiceRemote clienteService);
	public Cliente getById(long codigo);
	public List<Cliente> getAll();
	public Cliente create(Cliente cliente);
	
	public Cliente update(Cliente cliente);
	
	public void delete(long codigo);
}
