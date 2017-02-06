package com.ipartek.formacion.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	 * Metodo para borrar un alumno
	 * la url es unica.
	 */
	@RequestMapping(value = "/addAlumno")
	public String addAlumno(Model model){
		model.addAttribute("alumno",new Alumno());
		return "/alumnos/alumno";
	}
	/**
	 * metodo para salvar el alumno
	 * @param alumno
	 * @param model
	 * @return
	 */
	@RequestMapping(method =  RequestMethod.POST)
	public String saveAlumno(@ModelAttribute("alumno")Alumno alumno, Model model){
		String destino ="";
		if(alumno.getCodigo() > Alumno.CODIGO_NULO){
			aS.update(alumno);
		}else{
			aS.create(alumno);
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