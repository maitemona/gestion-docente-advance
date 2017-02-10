package com.ipartek.formacion.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ipartek.formacion.dbms.persistence.Alumno;
import com.ipartek.formacion.dbms.persistence.Profesor;
import com.ipartek.formacion.service.interfaces.AlumnoService;
import com.ipartek.formacion.service.interfaces.ProfesorService;

@Controller
@RequestMapping(value = "/profesores")
public class ProfesorController {
	
	/*busca una clase q lo implemente(@Autowired)*/
	@Inject
	private ProfesorService pS;
	private static final Logger logger = LoggerFactory.getLogger(ProfesorController.class);
	ModelAndView mav = null;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAll() {

		mav = new ModelAndView("/profesores/profesores");
		//Cargar a cargar lista de profesores
		List<Profesor> profesores = pS.getAll();
		//Engancharla  al modelandview
		logger.info("Controller lista size:"+profesores.size());
		mav.addObject("listadoProfesores",profesores);
		logger.trace("pasa por getAll() de profesores");
		return mav; 
	}	
	
	/*Cambiamos @validated por @valid q es estandar java y spring ya sabe como usar
	  sabe que esta en persitencia y no hay que inyectar nada como (initbinder)*/
	
	@RequestMapping(value="/save",method =  RequestMethod.POST)
	public String saveProfesor(Model model,@ModelAttribute("profesor") @Valid Profesor profesor, 
			 BindingResult bindingResult ){
		String destino ="";
		logger.info("Estoy en metdodo Save");
		/*si las cosas estan mal nos mande de vuelta*/
		if(bindingResult.hasErrors()){
			logger.info("profesor tiene errores");
			destino = "/profesores/profesor";
		}else{ 
			destino ="redirect:/profesores";
			if(profesor.getCodigo() > Profesor.CODIGO_NULO){
				logger.info(profesor.toString());
				pS.update(profesor);
			}else{
				logger.info(profesor.toString());
				pS.create(profesor);
			}
		}
		return destino;
	}
	/**
	 * Metodo para add un profesor
	 * la url es unica.
	 */
	@RequestMapping(value = "/addProfesor")
	public String addProfesor(Model model){
		model.addAttribute("profesor",new Profesor());
		logger.trace("pasa por addProfesor()");
		return "/profesores/profesor";
	}
	/*getbyid*/
	@RequestMapping(value = "/{id}")
	public ModelAndView getByid(@PathVariable("id") int id){
		
		mav = new ModelAndView("/profesores/profesor");
		mav.addObject("profesor", pS.getById(id));
		return mav;
	}
	
	
	
	/*Metod de Borrar*/
	@RequestMapping(value = "/deleteProfesor/{id}")
	public String deleteProfesor(@PathVariable("id") int id){
		pS.delete(id);
		return "redirect:/profesores";
		
	}







}





