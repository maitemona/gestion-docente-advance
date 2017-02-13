package com.ipartek.formacion.dbms.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ipartek.formacion.controller.AlumnoController;
import com.ipartek.formacion.dbms.dao.interfaces.AlumnoDAO;
import com.ipartek.formacion.dbms.mappers.AlumnoMapper;
import com.ipartek.formacion.dbms.persistence.Alumno;


@Repository("alumnoDaoImp")
public class AlumnoDAOImp  implements AlumnoDAO{
	
	@Inject 
	private DataSource dataSource;
	private JdbcTemplate template;
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
		
	}

	@Override
	public Alumno create(Alumno alumno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Alumno> getAll() {
//hjemos hecho un proceso de almacenimiento en BBDD,que se llama alumnogetAll
		final String SQL = "CALL alumnogetAll();";
	//	final String SQL="SELECT codigo as codigo, nombre as nombre, apellidos as apellido FROM `alumno`";
		List<Alumno> alumnos =null;	
		try{
			alumnos = template.query(SQL, new AlumnoMapper());
		}catch (EmptyResultDataAccessException e){
			logger.trace(e.getMessage());
			alumnos = new ArrayList<Alumno>(); 
		}
		return alumnos;
	}

	@Override
	public Alumno getById(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Alumno update(Alumno alumno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int codigo) {
		// TODO Auto-generated method stub
		
	}
	

}
