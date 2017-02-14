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
			alumno.setApellidos(rs.getString("apellidos"));
			alumno.setNombre(rs.getString("nombre"));
			alumno.setActivo(rs.getBoolean("activo"));
			alumno.setnHermanos(rs.getInt("nhermanos"));
			alumno.setDni(rs.getString("dni"));
			alumno.setfNacimiento(rs.getDate("fnacimiento"));
			alumno.setEmail(rs.getString("email"));
			alumno.setDireccion(rs.getString("direccion"));
			alumno.setPoblacion(rs.getString("poblacion"));
			alumno.setCodigopostal(rs.getInt("codigopostal"));
			alumno.setTelefono(String.valueOf(rs.getInt("telefono")));
				
		return alumno;
	}

}
