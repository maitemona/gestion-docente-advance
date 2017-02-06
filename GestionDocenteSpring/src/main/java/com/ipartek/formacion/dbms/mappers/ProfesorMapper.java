package com.ipartek.formacion.dbms.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ipartek.formacion.dbms.persistence.Alumno;
import com.ipartek.formacion.dbms.persistence.Profesor;

public class ProfesorMapper implements RowMapper<Profesor>{

	@Override
	public Profesor mapRow(ResultSet rs, int rowNum) throws SQLException {
		Profesor profesor = new Profesor();
		profesor.setCodigo(rs.getInt("codigo"));
		profesor.setNombre(rs.getString("nombre"));
		profesor.setApellidos(rs.getString("apellidos"));
		return profesor;
	}

}
