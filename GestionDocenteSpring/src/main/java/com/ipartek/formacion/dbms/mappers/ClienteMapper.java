package com.ipartek.formacion.dbms.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;


import com.ipartek.formacion.dbms.persistence.Cliente;

public class ClienteMapper implements RowMapper<Cliente> {

	private static final Logger logger = LoggerFactory.getLogger(ClienteMapper.class);
	
	@Override
	public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		
		Cliente cliente = new Cliente();
		
		cliente.setCodigo(rs.getInt("codigo"));
		cliente.setNombre(rs.getString("nombre"));
		cliente.setEmail(rs.getString("email"));
		cliente.setActivo(rs.getBoolean("activo"));
		cliente.setDireccion(rs.getString("direccion"));
		cliente.setIdentificador(rs.getString("identificador"));
		cliente.setTelefono(String.valueOf(rs.getInt("telefono")));
		
		return cliente;
	}
}
