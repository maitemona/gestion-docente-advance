package com.ipartek.formacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ipartek.formacion.service.interfaces.CursoService;

/**
 * vamos a procesar las peticiones de las vistas cursos
 * @author Curso
 *
 */
@Controller
@RequestMapping("/cursos")
public class CursoController {
	
	@Autowired
	private CursoService cS;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getAll(Model model){
		model.addAttribute("listadoCursos",cS.getAll());
		return"cursos/cursos";
	}
	
	@RequestMapping(value = "/{codigo}")
	public String getByid(@PathVariable("codigo") long codigo,Model model){
		model.addAttribute("curso", cS.getById(codigo));
		return "cursos/curso";
	}
	
}
