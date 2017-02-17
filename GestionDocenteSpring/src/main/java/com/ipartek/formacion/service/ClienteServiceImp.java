package com.ipartek.formacion.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;


import com.ipartek.formacion.dbms.dao.interfaces.ClienteDAO;

import com.ipartek.formacion.dbms.persistence.Cliente;
import com.ipartek.formacion.service.interfaces.ClienteService;

@Service
public class ClienteServiceImp implements ClienteService {
	
	@Inject
	private ClienteDAO clienteDao;
	
	
	@Override
	public Cliente create(Cliente cliente) {
		return clienteDao.create(cliente);
	}

	@Override
	public List<Cliente> getAll() {
		return clienteDao.getAll();
	}

	@Override
	public Cliente getById(int codigo) {
		return clienteDao.getById(codigo);
	}

	@Override
	public Cliente update(Cliente cliente) {
		return clienteDao.update(cliente);
	}

	@Override
	public void delete(int codigo) {
		clienteDao.delete(codigo);
		
	}

	@Override
	public Cliente getByDni(String identificador) {
		return clienteDao.getByDni(identificador);
	}

	@Override
	public void setClienteDAO(ClienteDAO clienteDao) {
		this.clienteDao=clienteDao;
		
	}

}
