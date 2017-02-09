package com.ipartek.formacion.dbms.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ipartek.formacion.dbms.dao.AlumnoDAOImp;
import com.ipartek.formacion.dbms.dao.interfaces.ProfesorDAO;
import com.ipartek.formacion.dbms.mappers.AlumnoMapper;
import com.ipartek.formacion.dbms.mappers.ProfesorMapper;
import com.ipartek.formacion.dbms.persistence.Alumno;
import com.ipartek.formacion.dbms.persistence.Profesor;

@Repository("profesorDaoImp")
public class ProfesorDAOImp implements ProfesorDAO {

	@Inject 
	private DataSource dataSource;
	private JdbcTemplate template;
	private static final Logger logger = LoggerFactory.getLogger(ProfesorDAOImp.class);
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
	public Profesor create(Profesor profesor) {
		
		return null;
	}

	@Override
	public List<Profesor> getAll() {
		final String SQL="SELECT codigo as codigo, nombre as nombre, apellidos as apellidos FROM profesor";
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Profesor update(Profesor profesor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int codigo) {
		// TODO Auto-generated method stub
		
	}

}