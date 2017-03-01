package com.ipartek.formacion.dbms.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;


import com.ipartek.formacion.dbms.dao.interfaces.ProfesorDAO;

import com.ipartek.formacion.dbms.mappers.ProfesorMapper;

import com.ipartek.formacion.dbms.persistence.Profesor;

@Repository("profesorDaoImp")
public class ProfesorDAOImp implements ProfesorDAO {

	@Autowired 
	private DataSource dataSource;
	private JdbcTemplate template;
	/*para pasar parametros al resto de metodos del mysql(al CAll no hace falta) */
	private SimpleJdbcCall jdbcCall;
	private static final Logger logger = LoggerFactory.getLogger(ProfesorDAOImp.class);
	/**
	 * queremos obligarle a exista, estamos creadio la conexion a al BBDD ,
	 *  iyeccion de depencias, se hace a traves del setter
	 */
	
	@Autowired//es lo mismo q Autowired
	@Override
	public void setDataSource(DataSource dataSource) {
		
		this.dataSource = dataSource;
		this.template= new JdbcTemplate(dataSource);
		this.jdbcCall= new SimpleJdbcCall(dataSource);
	}


	@Override
	public Profesor create(Profesor profesor) {
		
		final String SQL = "profesorCreate";
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		
		//llamamos al prodecimiento de create de la bbddd
		jdbcCall.withProcedureName(SQL);
		//crear un mapa con los prarametroos de procedimiento
		SqlParameterSource in =new MapSqlParameterSource()
				.addValue("pnombre",profesor.getNombre())
				.addValue("papellidos",profesor.getApellidos())
				.addValue("ppostal", profesor.getCodigopostal())
				.addValue("pdni", profesor.getDni())
				.addValue("pemail", profesor.getEmail())
				.addValue("pfnacimiento", profesor.getfNacimiento())
				.addValue("pnss", profesor.getnSS())
				.addValue("ppoblacion", profesor.getPoblacion())
				.addValue("ptelefono", profesor.getTelefono())
				.addValue("pdireccion", profesor.getDireccion());
		
		logger.info(profesor.toString());
		/*ejecutamos sentencia*/	
		//en out se han recogido los parametros out de la consulta BBDD
		Map<String, Object> out = jdbcCall.execute(in);
		//hacemos un casting pq es un int
		profesor.setCodigo((Integer)out.get("pcodigo"));
		return profesor;
	}

	@Override
	public List<Profesor> getAll() {	
		//hemos hecho un proceso de almacenimiento en BBDD,que se llama alumnogetAll
		final String SQL = "CALL profesorgetAll();";
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		
		logger.info(SQL);
		List<Profesor> profesores =null;	
		try{
			profesores = template.query(SQL, new ProfesorMapper());
			logger.info("Tamaño lista profesores: "+ profesores.size());
		}catch (EmptyResultDataAccessException e){
			logger.trace(e.getMessage());
			profesores = new ArrayList<Profesor>(); 
		}
		return profesores;
	}

	@Override
	public Profesor getById(int codigo) {
		Profesor profesor = null;
		final String SQL = "CALL profesorgetById(?)";
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		try{
			/*queryforobject es cuando vamos a tener 1 objeto*/
			profesor= template.queryForObject(SQL , new ProfesorMapper(), new Object[]{codigo});
			logger.info("select sql"+ SQL);
			logger.info(profesor.toString());
			logger.info("Codigo profesor "+profesor.getCodigo());
		}catch (EmptyResultDataAccessException e){
			//instanciamos nuevo objeto de alumno para que no casque
			profesor  = new Profesor();
			logger.info("No encontrado el codigo de profesor" + codigo + " "+ e.getMessage());
		}
		return profesor;
		
	}

	@Override
	public Profesor update(Profesor profesor) {
		final String SQL = "profesorUpdate";
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		//llamamos al prodecimiento de update de la bbddd
		jdbcCall.withProcedureName(SQL);
		//crear un mapa con los prarametroos de procedimiento
		SqlParameterSource in =new MapSqlParameterSource()
				.addValue("pnombre",profesor.getNombre())
				.addValue("papellidos",profesor.getApellidos())
				.addValue("ppostal", profesor.getCodigopostal())
				.addValue("pdni", profesor.getDni())
				.addValue("pemail", profesor.getEmail())
				.addValue("pfnacimiento", profesor.getfNacimiento())
				.addValue("pnss", profesor.getnSS())
				.addValue("ppoblacion", profesor.getPoblacion())
				.addValue("ptelefono", profesor.getTelefono())
				.addValue("pdireccion", profesor.getDireccion())
				.addValue("pcodigo", profesor.getCodigo());
		logger.info(profesor.toString());
		/*ejecutamos sentencia*/	
		jdbcCall.execute(in);
		
		return profesor;
	}

	@Override
	public void delete(int codigo) {
		final String SQL= "profesorDelete";
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		jdbcCall.withProcedureName(SQL);
		SqlParameterSource in =new MapSqlParameterSource()
				.addValue("pcodigo", codigo);
		
		logger.info(String.valueOf(codigo));
		jdbcCall.execute(in);
		
	}
	
	@Override
	public Profesor getByDni(String dni) {
		Profesor profesor = null;
		final String SQL = "CALL profesorgetByDni(?)";
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		try{
			/*queryforobject es cuando vamos a tener 1 objeto*/
			profesor = template.queryForObject(SQL , new ProfesorMapper(), new Object[]{dni});
			logger.info("select sql"+ SQL);
			logger.info(profesor.toString());
			logger.info("Dni profesor "+profesor.getDni());
		}catch (EmptyResultDataAccessException e){
			//instanciamos nuevo objeto de alumno para que no casque
			profesor  = null;
			logger.info("no se ha encontrado el profesor con " + dni + " "+ e.getMessage());
		}
		logger.info("Devuelvo:"+profesor);
		return profesor;
	}
}