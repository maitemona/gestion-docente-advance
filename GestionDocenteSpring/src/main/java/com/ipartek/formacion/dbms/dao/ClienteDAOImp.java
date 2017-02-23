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

import com.ipartek.formacion.dbms.dao.interfaces.ClienteDAO;
import com.ipartek.formacion.dbms.mappers.ClienteExtractor;
import com.ipartek.formacion.dbms.mappers.ClienteMapper;

import com.ipartek.formacion.dbms.persistence.Cliente;
/*Poner el bean de root-contetx el bean id="clienteDaoImp"
		class="com.ipartek.formacion.dbms.dao.ClienteDAOImp">
		<property name="dataSource" ref="mysqlDataSource"/>
	</bean>*/



@Repository("clienteDaoImp")
public class ClienteDAOImp implements ClienteDAO {

	
	
	@Inject 
	private DataSource dataSource;
	private JdbcTemplate template;
	/*para pasar parametros al resto de metodos del mysql(al CAll no hace falta) */
	private SimpleJdbcCall jdbcCall;
	private static final Logger logger = LoggerFactory.getLogger(ClienteDAOImp.class);

	/**
	 * queremos obligarle a exista, estamos creadio la conexion a al BBDD ,
	 *  iyeccion de depencias, se hace a traves del setter
	 */
	@Autowired//@Inject
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.template= new JdbcTemplate(dataSource);
		this.jdbcCall= new SimpleJdbcCall(dataSource);
	}

	@Override
	public Cliente create(Cliente cliente) {
		final String SQL = "clienteCreate";
		
		logger.info("Metodo:"+ SQL);
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		
		//llamamos al prodecimiento de create de la bbddd
		jdbcCall.withProcedureName(SQL);
		//crear un mapa con los prarametroos de procedimiento
		SqlParameterSource in =new MapSqlParameterSource()
				.addValue("pnombre",cliente.getNombre())
				.addValue("pidentificador",cliente.getIdentificador())
				.addValue("pemail", cliente.getEmail())
				.addValue("ptelefono", cliente.getTelefono())
				.addValue("pdireccion", cliente.getDireccion());
		
		logger.info(cliente.toString());
		/*ejecutamos sentencia*/	
		//en out se han recogido los parametros out de la consulta BBDD
		Map<String, Object> out = jdbcCall.execute(in);
		//hacemos un casting pq es un int
		cliente.setCodigo((Integer)out.get("pcodigo"));
		return cliente;
	}

	@Override
	public List<Cliente> getAll() {
		final String SQL = "CALL clientegetAll();";
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		logger.info(SQL);
		List<Cliente> clientes =null;	
		
		try{
									
			clientes = template.query(SQL, new ClienteMapper());
			logger.info("Tamaño lista clientes: "+ clientes);
		}catch (EmptyResultDataAccessException e){
			
			logger.trace(e.getMessage());
			clientes = new ArrayList<Cliente>(); 
		}
		logger.info("Tamaño lista clientes: "+ clientes.size());
		return clientes;
	}

	@Override
	public Cliente getById(int codigo) {
		Cliente cliente = null;
		final String SQL = "CALL clientegetById(?)";
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		try{
			/*queryforobject es cuando vamos a tener 1 objeto*/
			cliente= template.queryForObject(SQL , new ClienteMapper(), new Object[]{codigo});
			logger.info("select sql"+ SQL);
			logger.info(cliente.toString());
			logger.info("Codigo Cliente "+cliente.getCodigo());
		}catch (EmptyResultDataAccessException e){
			//instanciamos nuevo objeto de alumno para que no casque
			cliente  = new Cliente();
			logger.info("No encontrado el codigo de cliente" + codigo + " "+ e.getMessage());
		}
		return cliente;
	}

	@Override
	public Cliente update(Cliente cliente) {
		final String SQL = "clienteUpdate";
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		//llamamos al prodecimiento de update de la bbddd
		jdbcCall.withProcedureName(SQL);
		//crear un mapa con los prarametroos de procedimiento
		SqlParameterSource in =new MapSqlParameterSource()
				.addValue("pnombre",cliente.getNombre())
				.addValue("pidentificador", cliente.getIdentificador())
				.addValue("pemail", cliente.getEmail())
				.addValue("ptelefono", cliente.getTelefono())
				.addValue("pdireccion", cliente.getDireccion())
				.addValue("pcodigo", cliente.getCodigo());
		logger.info(cliente.toString());
		/*ejecutamos sentencia*/	
		jdbcCall.execute(in);
		
		return cliente;
	}

	@Override
	public void delete(int codigo) {
		final String SQL= "clienteDelete";
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		jdbcCall.withProcedureName(SQL);
		SqlParameterSource in =new MapSqlParameterSource()
				.addValue("pcodigo", codigo);
		
		logger.info(String.valueOf(codigo));
		jdbcCall.execute(in);
		
	}
	
	@Override
	public Cliente getByDni(String identificador) {
		Cliente cliente = null;
		final String SQL = "CALL clientegetByDni(?);";
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		try{
			/*queryforobject es cuando vamos a tener 1 objeto*/
			cliente= template.queryForObject(SQL , new ClienteMapper(), new Object[]{identificador});
			logger.info("select sql"+ SQL);
			logger.info(cliente.toString());
			logger.info("Dni cliente "+cliente.getIdentificador());
		}catch (EmptyResultDataAccessException e){
			//instanciamos nuevo objeto de alumno para que no casque
			//cliente  = null;
			logger.info("sin datos:" + e.getMessage() + " " + SQL);
			logger.info("no se ha encontrado el cliente con identificador " + identificador + " "+ e.getMessage());
		}
		return cliente;
	}

	/*ES solo para los cursos de  un cliente*/
	
	@Override
	public Cliente getInforme(int codigo) {
		//Estamos trabajando con JBOSS
		final String SQL = "CALL clienteInforme(?);";
		Cliente cliente = null;
		try{
			logger.info("Codigo:"+ codigo);
			logger.info("Datos "+ SQL);
	
			Map<Long, Cliente> clientes = template.query(SQL ,new ClienteExtractor() ,new Object[]{codigo});
			//para coger el codigo de ese cliente
			cliente = clientes.get(codigo);
			//logger.info(arg0);
		}catch(EmptyResultDataAccessException e){
			cliente =null;
			logger.info("Sin datos de cliente o de cursos asigandos"+ e.getMessage()+""+SQL);
		}
		return cliente;
	}

}
