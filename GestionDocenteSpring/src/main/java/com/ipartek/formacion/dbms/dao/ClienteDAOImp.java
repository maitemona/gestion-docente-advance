package com.ipartek.formacion.dbms.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ipartek.formacion.dbms.dao.interfaces.ClienteDAO;

import com.ipartek.formacion.dbms.mappers.ClienteMapper;
import com.ipartek.formacion.dbms.mappers.ProfesorMapper;
import com.ipartek.formacion.dbms.persistence.Cliente;
/*Poner el bean de root-contetx el bean id="clienteDaoImp"
		class="com.ipartek.formacion.dbms.dao.ClienteDAOImp">
		<property name="dataSource" ref="mysqlDataSource"/>
	</bean>*/


@Repository("clienteDaoImp")
public class ClienteDAOImp implements ClienteDAO {

	
	
	@Inject 
	private DataSource dataSource;
	private JdbcTemplate template;
	private static final Logger logger = LoggerFactory.getLogger(ClienteDAOImp.class);

	/**
	 * queremos obligarle a exista, estamos creadio la conexion a al BBDD ,
	 *  iyeccion de depencias, se hace a traves del setter
	 */
	@Autowired//@Inject
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.template= new JdbcTemplate(dataSource);
		
	}

	@Override
	public Cliente create(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> getAll() {
		final String SQL="SELECT codigo as codigo, nombre as nombre, direccion as direccion, telefono as telefono, email as email, identificador as identificador FROM cliente";
		logger.info(SQL);
		List<Cliente> clientes =null;	
		
		try{
									
			clientes = template.query(SQL, new ClienteMapper());
			logger.info("Tamaño lista clientes: "+ clientes);
		}catch (EmptyResultDataAccessException e){
			
			logger.trace(e.getMessage());
			clientes = new ArrayList<Cliente>(); 
		}
		logger.info("Tamaño lista clientes: "+ clientes.size());
		return clientes;
	}

	@Override
	public Cliente getById(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente update(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int codigo) {
		// TODO Auto-generated method stub
		
	}

}
