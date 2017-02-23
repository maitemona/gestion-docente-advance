package com.ipartek.formacion.dbms.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.ipartek.formacion.dbms.dao.AlumnoDAOImp;
import com.ipartek.formacion.dbms.persistence.Cliente;
import com.ipartek.formacion.persistencia.Curso;
//el ResultSetExtractor tiene que ir el objeto que se va a devolver, que es un cliente
public class ClienteExtractor implements ResultSetExtractor<Map<Long,Cliente>>{
	private static final Logger logger = LoggerFactory.getLogger(ClienteExtractor.class);
	@Override
	public Map<Long,Cliente> extractData(ResultSet rs) throws SQLException, DataAccessException {
	/*hago un mapa par ver si esta mi cliente y asi recoger los cursos de ese cliente
	 * 
	 * 
	 * vamos a tener este Mapa para guardar los cliente/ yo cliente
	*/	
		
		Map<Long,Cliente> clientes = new HashMap<Long,Cliente>();
		//Cliente cliente = new Cliente();
		
		while (rs.next())
		{
			Long codigo= rs.getLong("codigo");
			//cliente.setCodigo(rs.getInt("codigo"));
			//recogemos el cliente del mapa
			Cliente cliente = clientes.get(codigo);
			
			if (cliente == null){
				
				cliente = new Cliente();
				cliente.setNombre(rs.getString("nombre"));
				cliente.setEmail(rs.getString("email"));
				cliente.setActivo(rs.getBoolean("activo"));
				cliente.setDireccion(rs.getString("direccion"));
				//cliente.setPoblacion(rs.getString("poblacion"));
				//cliente.setCodigopostal(rs.getInt("codigopostal"));
				cliente.setDireccion(rs.getString("poblacion"));
				cliente.setIdentificador(rs.getString("identificador"));
				cliente.setTelefono(String.valueOf(rs.getInt("telefono")));
				
				//cogemos los cursos del cliente
				cliente.setCursos(new HashMap <Long, Curso>());
			
			}
			
			//obtenemos el mapa del curso del cliente
			Map<Long, Curso> cursos = cliente.getCursos();
			Curso curso =new Curso();
			curso.setCodigo(rs.getLong("codigocurso"));
			curso.setNombre(rs.getString("nombrecurso"));
			curso.setfFin(rs.getDate("ffin"));
			curso.setfInicio(rs.getDate("finicio"));
			curso.setnHoras(rs.getInt("nhoras"));
			
			//ayadimos curso al mapa de cursos
			cursos.put(curso.getCodigo(), curso);
			//guardas el mapa en el cliente
			cliente.setCursos(cursos);
			//guardaras el cliente en la lista clientes
			clientes.put((long)cliente.getCodigo(), cliente);
		}
		
		//recogemos cursos al cliente, pq ya tenemos definido la lista cursos en cliente
		logger.info("nº clientes: "+clientes.size());
		//yo devuelvo un Mapa
		return clientes;
	}
	
	

}
