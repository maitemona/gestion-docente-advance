package com.ipartek.formacion.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
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
}