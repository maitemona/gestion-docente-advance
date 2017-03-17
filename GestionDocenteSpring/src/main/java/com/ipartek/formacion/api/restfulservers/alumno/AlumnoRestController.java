package com.ipartek.formacion.api.restfulservers.alumno;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ipartek.formacion.controller.validator.AlumnoValidator;
import com.ipartek.formacion.dbms.persistence.Alumno;
import com.ipartek.formacion.service.interfaces.AlumnoService;
/*
 * http://gestionformacion/api/alumns/1
 * metodo : get
 * respuesta codigo HTTP(el protocolo de estado de http siempre se devuelve)
 * 			alumno serializado en json, xml, html...
 * y la clase de spring que me gestiona esto es ResponseEntity
 * 
 * CrossFilter choca con jboss pq ya tenemos una inyection que es @crossorigin, dejo el crossfilter pero no vale,
 * lo puedo poner a nivel de clase, metodo y a nivel de proyecto mirar el servlet - contetx
 */
@CrossOrigin(origins="*" ,maxAge = 3600 , methods = {RequestMethod.GET ,RequestMethod.POST , RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping(value = "/api/alumnos" )
public class AlumnoRestController implements Serializable {
	/*
	 * @Autowired === @Inject
	 * @EJB == @Inject
	 */
	private static final long serialVersionUID = -6698866485450376235L;
	@Autowired
	AlumnoService aS;
	//inyectar dependencia
	 

	 
	 @Resource(name="alumnoValidator")
	 Validator validator;
	
	/*
	 * otra forma d ehacerlo 
	@Autowired
	AlumnoValidator validator;
	//Using spring’s validator interface
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.setValidator(new AlumnoValidator());
	}
	
	*/
	 @InitBinder
		protected void initBinder(WebDataBinder binder) {
		    binder.setValidator(validator);
		}
	
	 @RequestMapping(value = "/{codigo}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Alumno> getById(@PathVariable("codigo") int id){
		//recogemos un obejto alumno
		Alumno alumno = aS.getById(id);
		//recogemos un objeto ResponseEntity
		ResponseEntity<Alumno> reponse = null;
				
		 if(alumno == null){//404
			 reponse = new ResponseEntity<Alumno>(HttpStatus.NOT_FOUND);
		 }else{//200
			 reponse = new ResponseEntity<Alumno>(alumno , HttpStatus.OK);
		 }
		
		return reponse;
	}
	/*
	 * http://gestionformacion/api/alumns/1
	 * metodo : get
	 * */
		@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Alumno>> getAll(){
		
		List<Alumno> alumnos = aS.getAll(); 
		ResponseEntity<List<Alumno>> reponse = null;
		if(alumnos == null || alumnos.isEmpty()){//204
			 reponse = new ResponseEntity<List<Alumno>>(HttpStatus.NO_CONTENT);
		 }else{//200
			 reponse = new ResponseEntity<List<Alumno>>(alumnos , HttpStatus.OK);
		 }
		return reponse;
	}
	/*
	/*
	 * http://gestionformacion/api/alumnos
	 * metodo : post
	 * */
		@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> create(@Valid @RequestBody Alumno alumno, UriComponentsBuilder ucBuilder){
		
		
		//validamos que ese alumno esixta o no con el metos getByDni
		Alumno alum= aS.getByDni(alumno.getDni());
		ResponseEntity<Void> response = null;
		if(alum != null){//409
			response = new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}else{//300
			try{
				//recogo el objeto por el codigo
				Alumno aux = aS.create(alumno);
				//paar devolver el obejto garbado en bbdd, hay que manipular los encabezados de HTTP
				//vamos a llamar al metodo  ResponseEntity<Alumno> getById
				//response = new ResponseEntity<Void>(HttpStatus.CREATED);
				HttpHeaders headers = new HttpHeaders();
				headers.setLocation(ucBuilder.path("/api/alumnos/{codigo}").buildAndExpand(aux.getCodigo()).toUri());
				response = new ResponseEntity<Void>(headers,HttpStatus.CREATED);
			}catch(Exception e){
				response = new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
			}
		}
		return response;
	}
	/*
	 * http://gestionformacion/api/alumnos/1
	 * metodo : put
	 * */
		@RequestMapping(value = "/{codigo}", consumes = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.PUT, produces = {
						MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Alumno> update(@PathVariable("codigo") int id,@Valid @RequestBody Alumno alumno){
		Alumno alum = aS.getById(id);
		//recogemos un objeto ResponseEntity
		ResponseEntity<Alumno> response = null;			
		if(alum == null){
		response =  new ResponseEntity<Alumno>(HttpStatus.NOT_FOUND);
		}else{
			alum = aS.update(alumno);
			//paar devolver el obejto garbado en bbd
			response =  new ResponseEntity<Alumno>(alum , HttpStatus.ACCEPTED);
		}
		
		return response;
	}

	/*
	 * http://gestionformacion/api/alumns/1
	 * metodo : delete
	 * 
	 */
		@RequestMapping(value = "/{codigo}", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Alumno> deleteById(@PathVariable("codigo") int id){
		Alumno alum = aS.getById(id);
		ResponseEntity<Alumno> response = null;		
		
		if(alum == null){
			response =  new ResponseEntity<Alumno>(HttpStatus.NOT_FOUND);
		}else{
			aS.delete(id);
				//paar devolver el obejto garbado en bbd
			response =  new ResponseEntity<Alumno>(alum , HttpStatus.ACCEPTED);
		}
		return response;
	}


}