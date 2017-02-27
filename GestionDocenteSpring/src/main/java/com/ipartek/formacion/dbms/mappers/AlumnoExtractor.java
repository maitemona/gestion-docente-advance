package com.ipartek.formacion.dbms.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.ipartek.formacion.dbms.persistence.Alumno;

import com.ipartek.formacion.persistencia.Curso;

public class AlumnoExtractor implements ResultSetExtractor<Map<Integer,Alumno>> {
	private static final Logger logger = LoggerFactory.getLogger(AlumnoExtractor.class);

	@Override
	public Map<Integer, Alumno> extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		
		Map<Integer,Alumno> alumnos = new HashMap<Integer,Alumno>();
		
		while (rs.next())
		{
			int codigo= rs.getInt("codigo");
			//cliente.setCodigo(rs.getInt("codigo"));
			//recogemos el cliente del mapa
			Alumno alumno = alumnos.get(codigo);
			logger.info("code: "+codigo);
			//Long cCurso =  rs.getLong("codigocurso");
			if (alumno == null){
				
				alumno = new Alumno();
				
				//alumno.setCodigo(rs.getInt("codigo"));
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
				alumno.setCodigo(codigo);
				//cogemos los cursos del cliente
				//cliente.setCursos(new HashMap <Integer, Curso>());
				alumnos.put(alumno.getCodigo(), alumno);
			
			}
			
			Long aCurso = rs.getLong("codigocurso");
			logger.info(aCurso+"cnº cursos 1: " + alumno.getCursos().size());
			if (aCurso != null) {
				//obtenemos el mapa del curso del cliente
				//Map<Long, Curso> cursos = cliente.getCursos();
				Curso curso =new Curso();
				curso.setCodigo(rs.getLong("codigocurso"));
				curso.setNombre(rs.getString("nombrecurso"));
				curso.setfFin(rs.getDate("ffin"));
				curso.setfInicio(rs.getDate("finicio"));
				curso.setnHoras(rs.getInt("nhoras"));
				curso.setPrecio(rs.getDouble("precio"));
				alumno.getCursos().put(aCurso, curso);
				logger.info("Tam cursos"+ alumno.getCursos().size());
				//ayadimos curso al mapa de cursos
				//cursos.put(curso.getCodigo(), curso);
				//guardas el mapa en el cliente
			//	cliente.setCursos(cursos);
				//guardaras el cliente en la lista clientes
				//clientes.put(cliente.getCodigo(), cliente);
				logger.info("Horas"+ curso.getnHoras());
			}
		}
		
		//recogemos cursos al cliente, pq ya tenemos definido la lista cursos en cliente
		logger.info("nº alumnos: "+alumnos.size()+""+ alumnos.toString());
		//yo devuelvo un Mapa
		return alumnos;
	}
	

}
