package com.ipartek.formacion.dbms.dao;

import java.util.List;

import javax.sql.DataSource;
import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ipartek.formacion.dbms.dao.interfaces.AlumnoDAO;
import com.ipartek.formacion.dbms.mappers.AlumnoMapper;
import com.ipartek.formacion.dbms.persistence.Alumno;


@Repository("alumnoDaoImp")
public class AlumnoDAOImp  implements AlumnoDAO{
	
	@Inject 
	private DataSource dataSource;
	private JdbcTemplate template;

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
		final String SQL="Select * FROM alumno";
		List<Alumno> alumnos =null;	
		alumnos = (List<Alumno>)template.queryForObject(SQL, new AlumnoMapper());
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
	public Alumno delete(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
