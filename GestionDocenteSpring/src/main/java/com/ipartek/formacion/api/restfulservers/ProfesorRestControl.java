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

import com.ipartek.formacion.dbms.persistence.Alumno;
import com.ipartek.formacion.dbms.persistence.Profesor;
import com.ipartek.formacion.service.interfaces.ProfesorService;


@RestController
@RequestMapping(value = "/api/profesores" )
public class ProfesorRestControl implements Serializable { 
	
	private static final long serialVersionUID = -6698866485450376235L;
	@Autowired
	ProfesorService pS;
	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
	public ResponseEntity<Profesor> getById(@PathVariable("codigo") String id){
		
		Profesor profesor = pS.getByDni(id);
		//recogemos un objeto ResponseEntity
		ResponseEntity<Profesor> reponse = null;
		 if(profesor == null){//404
			 reponse = new ResponseEntity<Profesor>(HttpStatus.NOT_FOUND);
		 }else{//200
			 reponse = new ResponseEntity<Profesor>(profesor , HttpStatus.OK);
		 }
		
		return reponse;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Profesor>> getAll(){
		List<Profesor> profesores = pS.getAll(); 
		ResponseEntity<List<Profesor>> reponse = null;
		if(profesores == null || profesores.isEmpty()){//204
			 reponse = new ResponseEntity<List<Profesor>>(HttpStatus.NO_CONTENT);
		 }else{//200
			 reponse = new ResponseEntity<List<Profesor>>(profesores , HttpStatus.OK);
		 }
		return reponse;
	}
	
	
	public ResponseEntity<Void> create(@RequestBody Profesor profesor, UriComponentsBuilder ucBuilder){
		//validamos que ese alumno esixta o no con el metos getByDni
				Profesor profe= pS.getByDni(profesor.getDni());
				ResponseEntity<Void> response = null;
				if(profe != null){//409
					response = new ResponseEntity<Void>(HttpStatus.CONFLICT);
				}else{//300
					try{
						//recogo el objeto por el codigo
						Profesor aux = pS.create(profesor);
						//paar devolver el obejto garbado en bbdd, hay que manipular los encabezados de HTTP
						//vamos a llamar al metodo  ResponseEntity<Alumno> getById
						//response = new ResponseEntity<Void>(HttpStatus.CREATED);
						HttpHeaders headers = new HttpHeaders();
						headers.setLocation(ucBuilder.path("/api/prefesores/{codigo}").buildAndExpand(aux.getCodigo()).toUri());
						response = new ResponseEntity<Void>(headers,HttpStatus.CREATED);
					}catch(Exception e){
						response = new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
					}
				}
				return response;
	}
	
	@RequestMapping(value = "/{codigo}", method = RequestMethod.DELETE)
	public ResponseEntity<Profesor> deleteById(@PathVariable("codigo") int id){
		Profesor profe = pS.getById(id);
		ResponseEntity<Profesor> response = null;		
		
		if(profe == null){
			response =  new ResponseEntity<Profesor>(HttpStatus.NOT_FOUND);
		}else{
			pS.delete(id);
				//paar devolver el obejto garbado en bbd
			response =  new ResponseEntity<Profesor>(profe , HttpStatus.ACCEPTED);
		}
		return response;
	}
	
	@RequestMapping(value = "/{codigo}", method = RequestMethod.PUT)
	public ResponseEntity<Profesor> update(@PathVariable("codigo") int id,@RequestBody Profesor profesor){
		Profesor profe = pS.getById(id);
		//recogemos un objeto ResponseEntity
		ResponseEntity<Profesor> response = null;			
		if(profe == null){
			response =  new ResponseEntity<Profesor>(HttpStatus.NOT_FOUND);
		}else{
			profe = pS.update(profesor);
			//paar devolver el obejto garbado en bbd
			response =  new ResponseEntity<Profesor>(profe , HttpStatus.ACCEPTED);
		}
		
		return response;
		
	}
}
