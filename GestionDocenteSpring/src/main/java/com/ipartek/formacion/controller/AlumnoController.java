package com.ipartek.formacion.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.ipartek.formacion.dbms.persistence.Alumno;
import com.ipartek.formacion.service.interfaces.AlumnoService;

@Controller
@RequestMapping(value = "/alumnos")
public class AlumnoController {
	
	/*busca una clase q lo implemente(@Autowired)*/
	@Inject
	private AlumnoService aS;
	private static final Logger logger = LoggerFactory.getLogger(AlumnoController.class);
	ModelAndView mav = null;
	
	/*Cuando usemoss el validor de string tiene que esta cargada al iniciarse el controller,
	  y somos la clase de spring*/
	/*llamomos al id del bean del servlet-context*/
	
	/*otra forma de ponerlo, solo q lo ultima que busca es el nombre*/
	//@Autowired//@Qualifiar("alumnoValidator")
	
	
	@Resource(name="alumnoValidator")
	private Validator validator = null;
	/*metodo que alimente ese init que hemos hecho: estamos en SRIPNG*/
	@InitBinder
	private void initBinder(WebDataBinder binder){
		
		binder.setValidator(validator);
		/*el campo fecha es especiL, se valida asi*/
		binder.registerCustomEditor
		(Date.class,new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), false, 10));
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAll() {

		mav = new ModelAndView("/alumnos/alumnos");
		//Cargar a cargar lista de alumnos
		List<Alumno> alumnos = aS.getAll();
		//Engancharla  al modelandview
		mav.addObject("listadoAlumnos",alumnos);
		logger.trace("pasa por getAll()");
		return mav; 
	}	
	/**
	 * Metodo para add un alumno
	 * la url es unica.
	 */
	@RequestMapping(value = "/addAlumno")
	public String addAlumno(Model model){
		model.addAttribute("alumno",new Alumno());
		return "/alumnos/alumno";
	}
	/**
	 * metodo para salvar el alumno, sabremos si es update o create.
	 * 
	 * Vamos a validar usando el spring validor @Validated, en este metodo.
	 * el standar de java es @valid
	 * asociacion de la validacion: BindingResult bindingResult
	 * Que me carga la lista y me saque el listado, es el que tiene metodo GET: destino ="redirect :/alumnos";
	 * @param alumno
	 * @param Comntroller
	 * @return
	 */
	@RequestMapping(value="/save",method =  RequestMethod.POST)
	public String saveAlumno (Model model , @ModelAttribute("alumno") @Validated Alumno alumno, BindingResult bindingResult ){
		String destino ="";
		/*si las cosas estan mal nos mande de vuelta*/
		if(bindingResult.hasErrors()){
			logger.info("alumno tiene errores");
			destino = "/alumnos/alumno";
		}else{ 
			destino ="redirect:/alumnos";
			if(alumno.getCodigo() > Alumno.CODIGO_NULO){
				logger.info(alumno.toString());
				aS.update(alumno);
			}else{
				logger.info(alumno.toString());
				aS.create(alumno);
			}
		}
		return destino;
	}
	
	@RequestMapping(value = "/{id}")
	public ModelAndView getByid(@PathVariable("id") int id){
		
		mav = new ModelAndView("/alumnos/alumno");
		mav.addObject("alumno", aS.getById(id));
		return mav;
	}
	
	
	
	@RequestMapping(value = "/deleteAlumno/{id}")
	public String deleteAlumno(@PathVariable("id") int id){
		aS.delete(id);
		return "redirect:/alumnos";
		
	}
	
}