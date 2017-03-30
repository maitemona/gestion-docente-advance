package com.ipartek.formacion.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ipartek.formacion.cliente.ClienteServiceRemote;
import com.ipartek.formacion.persistencia.Cliente;
import com.ipartek.formacion.persistencia.Profesor;
import com.ipartek.formacion.service.interfaces.ClienteServiceEJB;

@Service("clienteServiceEJB")
public class ClienteServiceEJBImp implements ClienteServiceEJB{

	@Resource(name = "clienteServiceRemote")//siempre se accede asi por si acaso
	private ClienteServiceRemote clienteServiceRemote;
	
	@Override
	public void setClienteServiceRemote(ClienteServiceRemote clienteService) {
		this.clienteServiceRemote = clienteService;
		
	}

	@Override
	public Cliente getById(long codigo) {
		return clienteServiceRemote.getById(codigo);
	}

	@Override
	public List<Cliente> getAll() {
		return clienteServiceRemote.getAll();
	}

	@Override
	public Cliente create(Cliente cliente) {
		return clienteServiceRemote.create(cliente);
	}

	@Override
	public Cliente update(Cliente cliente) {
		return clienteServiceRemote.update(cliente);
	}

	@Override
	public void delete(long codigo) {
		Cliente cliente = clienteServiceRemote.getById(codigo);
		cliente.setActivo(false);
		clienteServiceRemote.update(cliente);
		
	}

}
