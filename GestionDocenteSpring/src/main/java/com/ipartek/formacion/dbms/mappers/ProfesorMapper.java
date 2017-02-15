package com.ipartek.formacion.dbms.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.ipartek.formacion.dbms.persistence.Profesor;

public class ProfesorMapper implements RowMapper<Profesor>{

	@Override
	public Profesor mapRow(ResultSet rs, int rowNum) throws SQLException {
		Profesor profesor = new Profesor();
		profesor.setCodigo(rs.getInt("codigo"));
		profesor.setNombre(rs.getString("nombre"));
		profesor.setApellidos(rs.getString("apellidos"));
		profesor.setnSS(rs.getString("nss"));
		profesor.setActivo(rs.getBoolean("activo"));
		profesor.setDni(rs.getString("dni"));
		profesor.setfNacimiento(rs.getDate("fnacimiento"));
		profesor.setEmail(rs.getString("email"));
		profesor.setDireccion(rs.getString("direccion"));
		profesor.setPoblacion(rs.getString("poblacion"));
		profesor.setCodigopostal(rs.getInt("codigopostal"));
		profesor.setTelefono(String.valueOf(rs.getInt("telefono")));
	
		return profesor;
	}

}
