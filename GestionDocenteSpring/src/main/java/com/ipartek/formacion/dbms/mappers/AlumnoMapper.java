/**
 * Impleneta un interfaz de framework 
 */
package com.ipartek.formacion.dbms.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ipartek.formacion.dbms.persistence.Alumno;

public class AlumnoMapper implements RowMapper<Alumno> {

	@Override
	public Alumno mapRow(ResultSet rs, int rowNum) throws SQLException {
		Alumno alumno = new Alumno();
		//es el nombre del aleas del atributo de la select de BBDD
		alumno.setCodigo(rs.getInt("codigo"));
		alumno.setNombre(rs.getString("nombre"));
		alumno.setApellidos(rs.getString("apellido"));
		return alumno;
	}

}
