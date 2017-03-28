package com.ipartek.formacion.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.ipartek.formacion.persistencia.Curso;
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
	private ModelAndView mav=null;
	private static final Logger logger = LoggerFactory.getLogger(CursoController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String getAll(Model model){
		model.addAttribute("listadoCursos",cS.getAll());
	
		return"cursos/cursos";
		
	}
	
	@RequestMapping(value = "/{codigo}")
	public String getByid(@PathVariable("codigo") long codigo,Model model){
		model.addAttribute("curso", cS.getById(codigo));
		return "cursos/cursoform";
	}
	 /**
	  * Metodo que me saca los detalles del modulo asociado a ese curos
	  * @param codigocurso
	  * @param codigodetalle
	  * @return
	  */
	@RequestMapping(value = "/{codigocurso}/detalles/{codigodetalle}")
		public ModelAndView getDetallesByCurso(@PathVariable("codigocurso") long codigocurso,
				@PathVariable("codigodetalle") long codigodetalle){
		
		return mav;
		
	}
	
	/**
	 * Metodo para sacar los alumnos asociados a ese curso
	 * @param codigocurso
	 * @param codigoalumno
	 * @return
	 */
	@RequestMapping(value = "/{codigocurso}/alumnos/{codigoalumno}")
	public ModelAndView getAlumnoByCurso(@PathVariable("codigocurso") long codigocurso,
			@PathVariable("codigoalumno") long codigoalumno){
	
	return mav;
	
	}
	/**
	 * Metodo para add un curso
	 * la url es unica.
	 */
	@RequestMapping(value = "/addCurso")
	public String addAlumno(Model model){
		model.addAttribute("curso",new Curso());
		return "/cursos/cursoform";
	}
	
	@RequestMapping(value="/save",method =  RequestMethod.POST)
	public String saveContacto (Model model , @ModelAttribute("curso") @Validated Curso curso, BindingResult bindingResult ){
		String destino ="";
		/*si las cosas estan mal nos mande de vuelta*/
		if(bindingResult.hasErrors()){
			logger.info("curso tiene errores");
			destino = "cursos/cursoform";
		}else{ 
			destino = "redirect:/cursos";
			if(curso.getCodigo() > Curso.CODIGO_NULO){
				logger.info("AQUI"+curso.toString());
				cS.update(curso);
			}else{
				logger.info("Objeto update:"+curso.toString());
				cS.create(curso);
			}
		}
		return destino;
	}
	
	@RequestMapping(value = "/deleteCurso/{codigo}")
	public String delete(@PathVariable("codigo") int codigo) {
		cS.delete(codigo);
		return "redirect:/cursos";
	}
	
	
	@RequestMapping(value = "/informe/{codigo}")
	public String verInforme(@PathVariable("codigo") int codigo,Model model) {
		logger.info("informe Curso");
		model.addAttribute("curso", cS.getById(codigo));
		return "cursos/curso";
		
	}

	
}
