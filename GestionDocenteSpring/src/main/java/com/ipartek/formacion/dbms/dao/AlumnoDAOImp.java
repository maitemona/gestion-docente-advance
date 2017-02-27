package com.ipartek.formacion.dbms.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;


import com.ipartek.formacion.dbms.dao.interfaces.AlumnoDAO;
import com.ipartek.formacion.dbms.mappers.AlumnoExtractor;
import com.ipartek.formacion.dbms.mappers.AlumnoMapper;

import com.ipartek.formacion.dbms.persistence.Alumno;



@Repository("alumnoDaoImp")
public class AlumnoDAOImp  implements AlumnoDAO{
	
	@Inject 
	private DataSource dataSource;
	private JdbcTemplate template;
	/*para pasar parametros al resto de metodos del mysql(al CAll no hace falta) */
	private SimpleJdbcCall jdbcCall;
	private static final Logger logger = LoggerFactory.getLogger(AlumnoDAOImp.class);

	/**
	 * queremos obligarle a exista, estamos creadio la conexion a al BBDD ,
	 *  iyeccion de depencias, se hace a traves del setter
	 */
	
	@Inject //es lo mismo q Autowired
	@Override
	public void setDataSource(DataSource dataSource) {
		
		this.dataSource = dataSource;
		this.template= new JdbcTemplate(dataSource);
		this.jdbcCall= new SimpleJdbcCall(dataSource);
		
	}

	@Override
	public Alumno create(Alumno alumno) {
		
		final String SQL = "alumnoCreate";
		//Alumno alum = null;=>return alum;
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		
		//llamamos al prodecimiento de create de la bbddd
		jdbcCall.withProcedureName(SQL);
		//crear un mapa con los prarametroos de procedimiento
		SqlParameterSource in =new MapSqlParameterSource()
				.addValue("pnombre",alumno.getNombre())
				.addValue("papellidos",alumno.getApellidos())
				.addValue("ppostal", alumno.getCodigopostal())
				.addValue("pdni", alumno.getDni())
				.addValue("pemail", alumno.getEmail())
				.addValue("pfnacimiento", alumno.getfNacimiento())
				.addValue("pnhermanos", alumno.getnHermanos())
				.addValue("ppoblacion", alumno.getPoblacion())
				.addValue("ptelefono", alumno.getTelefono())
				.addValue("pdireccion", alumno.getDireccion());
		
		logger.info(alumno.toString());
		/*ejecutamos sentencia*/	
		//en out se han recogido los parametros out de la consulta BBDD
		Map<String, Object> out = jdbcCall.execute(in);
		//hacemos un casting pq es un int
		alumno.setCodigo((Integer)out.get("pcodigo"));
		return alumno;
	}

	@Override
	public List<Alumno> getAll() {
	//hemos hecho un proceso de almacenimiento en BBDD,que se llama alumnogetAll
		final String SQL = "CALL alumnogetAll();";
		this.jdbcCall = new SimpleJdbcCall(dataSource);
	//	final String SQL="SELECT codigo as codigo, nombre as nombre, apellidos as apellido FROM `alumno`";
		List<Alumno> alumnos =null;	
		try{
			//jdbctemplate es para tadas las clapsulas select(*)
			/*query es cuando vamos a tener una lista de objetos*/
			alumnos = template.query(SQL, new AlumnoMapper());
		}catch (EmptyResultDataAccessException e){
			logger.trace(e.getMessage());
			alumnos = new ArrayList<Alumno>(); 
		}
		return alumnos;
	}

	@Override
	public Alumno getById(int codigo) {
		Alumno alumno = null;
		final String SQL = "CALL alumnogetById(?)";
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		try{
			/*queryforobject es cuando vamos a tener 1 objeto*/
			alumno= template.queryForObject(SQL , new AlumnoMapper(), new Object[]{codigo});
			logger.info("select sql"+ SQL);
			logger.info(alumno.toString());
			logger.info("Codigo alumno "+alumno.getCodigo());
		}catch (EmptyResultDataAccessException e){
			//instanciamos nuevo objeto de alumno para que no casque
			alumno  = new Alumno();
			logger.info("no se ha encontrado el codigop" + codigo + " "+ e.getMessage());
		}
		return alumno;
		
	}

	@Override
	public Alumno update(Alumno alumno) {
		final String SQL = "alumnoUpdate";
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		//llamamos al prodecimiento de update de la bbddd
		jdbcCall.withProcedureName(SQL);
		//crear un mapa con los prarametroos de procedimiento
		SqlParameterSource in =new MapSqlParameterSource()
				.addValue("pnombre",alumno.getNombre())
				.addValue("papellidos",alumno.getApellidos())
				.addValue("ppostal", alumno.getCodigopostal())
				.addValue("pdni", alumno.getDni())
				.addValue("pemail", alumno.getEmail())
				.addValue("pfnacimiento", alumno.getfNacimiento())
				.addValue("pnhermanos", alumno.getnHermanos())
				.addValue("ppoblacion", alumno.getPoblacion())
				.addValue("ptelefono", alumno.getTelefono())
				.addValue("pdireccion", alumno.getDireccion())
				.addValue("pcodigo", alumno.getCodigo());
		logger.info(alumno.toString());
		/*ejecutamos sentencia*/	
		jdbcCall.execute(in);
		
		return alumno;
	}

	@Override
	public void delete(int codigo) {
		final String SQL= "alumnoDelete";
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		jdbcCall.withProcedureName(SQL);
		SqlParameterSource in =new MapSqlParameterSource()
				.addValue("pcodigo", codigo);
		
		logger.info(String.valueOf(codigo));
		jdbcCall.execute(in);
		
	}

	@Override
	public Alumno getByDni(String dni) {
		Alumno alumno = null;
		final String SQL = "CALL alumnogetByDni(?)";
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		try{
			/*queryforobject es cuando vamos a tener 1 objeto*/
			alumno= template.queryForObject(SQL , new AlumnoMapper(), new Object[]{dni});
			logger.info("select sql"+ SQL);
			logger.info(alumno.toString());
			logger.info("Dni alumno "+alumno.getDni());
		}catch (EmptyResultDataAccessException e){
			//instanciamos nuevo objeto de alumno para que no casque
			alumno  = null;
			logger.info("no se ha encontrado el el " + dni + " "+ e.getMessage());
		}
		return alumno;
	}

	@Override
	public Alumno getInforme(int codigo) {
		
		//Estamos trabajando con JBOSS
		final String SQL = "CALL alumnoInforme(?);";
		Alumno alumno = null;
		try{
			logger.info("Codigo:"+ codigo);
			logger.info("Datos "+ SQL);
	
			Map<Integer, Alumno> alumnos = template.query(SQL ,new AlumnoExtractor() ,new Object[]{codigo});
			//para coger el codigo de ese cliente
			alumno = alumnos.get(codigo);
			//logger.info(arg0);
		}catch(EmptyResultDataAccessException e){
			alumno =null;
			logger.info("Sin datos de alumnos o de cursos asigandos"+ e.getMessage()+""+SQL);
		}
		return alumno;
	}

	

	
	
	
	
}
