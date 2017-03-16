package com.ipartek.formacion.api.restfulservers;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ipartek.formacion.dbms.persistence.Cliente;
import com.ipartek.formacion.dbms.persistence.Profesor;
import com.ipartek.formacion.service.interfaces.ClienteService;
import com.ipartek.formacion.service.interfaces.ProfesorService;

@RestController
@RequestMapping(value = "/api/clientes" )
public class ClienteRestController  implements Serializable { 
	
	private static final long serialVersionUID = -6698866485450376235L;
	@Autowired
	ClienteService cS;
	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> getById(@PathVariable("codigo") String id){
		
		Cliente cliente = cS.getByDni(id);
		//recogemos un objeto ResponseEntity
		ResponseEntity<Cliente> reponse = null;
		 if(cliente == null){//404
			 reponse = new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		 }else{//200
			 reponse = new ResponseEntity<Cliente>(cliente , HttpStatus.OK);
		 }
		
		return reponse;
	}
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Cliente>> getAll(){
		List<Cliente> clientes = cS.getAll(); 
		ResponseEntity<List<Cliente>> reponse = null;
		if(clientes == null || clientes.isEmpty()){//204
			 reponse = new ResponseEntity<List<Cliente>>(HttpStatus.NO_CONTENT);
		 }else{//200
			 reponse = new ResponseEntity<List<Cliente>>(clientes , HttpStatus.OK);
		 }
		return reponse;
	}
	public ResponseEntity<Void> create(@RequestBody Cliente cliente, UriComponentsBuilder ucBuilder){
		
				Cliente cli= cS.getByDni(cliente.getIdentificador());
				ResponseEntity<Void> response = null;
				if(cli != null){//409
					response = new ResponseEntity<Void>(HttpStatus.CONFLICT);
				}else{//300
					try{
						//recogo el objeto por el codigo
						Cliente aux = cS.create(cliente);
						HttpHeaders headers = new HttpHeaders();
						headers.setLocation(ucBuilder.path("/api/clientes/{codigo}").buildAndExpand(aux.getCodigo()).toUri());
						response = new ResponseEntity<Void>(headers,HttpStatus.CREATED);
					}catch(Exception e){
						response = new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
					}
				}
				return response;
	}
	
	@RequestMapping(value = "/{codigo}", method = RequestMethod.DELETE)
	public ResponseEntity<Cliente> deleteById(@PathVariable("codigo") int id){
		Cliente cli = cS.getById(id);
		ResponseEntity<Cliente> response = null;		
		
		if(cli == null){
			response =  new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		}else{
			cS.delete(id);
				//paar devolver el obejto garbado en bbd
			response =  new ResponseEntity<Cliente>(cli , HttpStatus.ACCEPTED);
		}
		return response;
	}
	
	@RequestMapping(value = "/{codigo}", method = RequestMethod.PUT)
	public ResponseEntity<Cliente> update(@PathVariable("codigo") int id,@RequestBody Cliente cliente){
		Cliente cli = cS.getById(id);
		//recogemos un objeto ResponseEntity
		ResponseEntity<Cliente> response = null;			
		if(cli == null){
			response =  new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		}else{
			cli = cS.update(cliente);
			//paar devolver el obejto garbado en bbd
			response =  new ResponseEntity<Cliente>(cli , HttpStatus.ACCEPTED);
		}
		
		return response;
		
	}

}
