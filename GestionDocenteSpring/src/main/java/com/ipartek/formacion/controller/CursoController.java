package com.ipartek.formacion.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ipartek.formacion.controller.pojo.Mensaje;
import com.ipartek.formacion.controller.pojo.MensajeType;
import com.ipartek.formacion.controller.validator.FileValidator;
import com.ipartek.formacion.persistencia.Alumno;
import com.ipartek.formacion.persistencia.Cliente;
import com.ipartek.formacion.persistencia.Curso;
import com.ipartek.formacion.persistencia.Profesor;
import com.ipartek.formacion.service.interfaces.AlumnoServiceEJB;
import com.ipartek.formacion.service.interfaces.ClienteServiceEJB;
import com.ipartek.formacion.service.interfaces.CursoService;
import com.ipartek.formacion.service.interfaces.ProfesorServiceEJB;

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
	@Autowired
	private ProfesorServiceEJB pS;
	@Autowired
	private ClienteServiceEJB cliS;
	@Autowired 
	private AlumnoServiceEJB aS;
//	@Autowired
	//CursoValidator validator;
	private ModelAndView mav=null;
	
	/////Queremos que nos metan el contexto de los servlet, queiro el context path , de (resources/docs)
	@Autowired
	private ServletContext servletContext;
	
	private static final Logger logger = LoggerFactory.getLogger(CursoController.class);

	
	
	@InitBinder("curso")
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		//binder.setValidator(validator);
	}
	
	
	@InitBinder("fichero")
	public void initBinderfichero(WebDataBinder binder) {
		
		binder.addValidators(new FileValidator());
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String getAll(Model model){
		model.addAttribute("listadoCursos",cS.getAll());
		return"cursos";
		
	}
	
	@RequestMapping(value = "/{codigo}")
	public String getByid(@PathVariable("codigo") long codigo,Model model){
		model.addAttribute("curso", cS.getById(codigo));
		return "cursoform";
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
	public String addCurso(Model model) {
		model.addAttribute("curso", new Curso());
		model.addAttribute("listadoProfesores", pS.getAll());
		model.addAttribute("listadoClientes", cliS.getAll());
		model.addAttribute("listadoAlumnos", aS.getAll());
		return "cursoform";
	}
	/*@RequestMapping(value = "/addCurso")
	public String addAlumno(Model model){
	//*para poner  a true el activo en bbdd, por defecto jpa todos los boleanos lo ponen a false*/
	/*	Curso curso = new Curso();
		curso.setActivo(true);
		model.addAttribute("curso",curso);
		return "/cursos/cursoform";
	}
	*/
	
	/*
	 * 
	 * Model 
	 * ModelMap
	 * REdirectAttributes
	 */
	//RedirectAttributes redirectMap, tiene la capacidad de mantenar los model pq al hacer redirect todo se pierde
	//(@Valid @RequestParam("fichero") MultipartFile  file .Es el archivo logico q envio
	@RequestMapping(value="/save",method =  RequestMethod.POST)
	public String saveCurso(@Valid @RequestParam("fichero") MultipartFile  file,@ModelAttribute(name = "curso") @Valid Curso curso, 
			BindingResult bindingResult,
			ModelMap model, RedirectAttributes redirectMap) throws IOException{
		String destino ="";
		
		Mensaje mensaje=null;
		String txt="";
		/*si las cosas estan mal nos mande de vuelta*/
		if(bindingResult.hasErrors()){
			logger.info("curso tiene errores");
			model.addAttribute("listadoProfesores", pS.getAll());
			model.addAttribute("listadoClientes",cliS.getAll());
			model.addAttribute("listadoAlumnos", aS.getAll());
			//List<Profesor> profesores = pS.getAll();
			//logger.info("tamaño de profesores:" + profesores.size());
		//	System.out.println("profesor"+pS.getAll());
			//logger.info(listadoProfesores.size());
			mensaje = new Mensaje(MensajeType.MSG_TYPE_DANGER);
			txt="Errores en los datos del formulario";
			model.addAttribute("mensaje",mensaje);
			destino = "cursoform";	
		}else{ 
			destino = "redirect:/cursos";
			//obtengo el archivo
			InputStream in= file.getInputStream();
			String root = File.separator + "resources" + File.separator + "docs" ;
			String ruta=servletContext.getRealPath(root);
			File destination = new File(ruta +File.separator+file.getOriginalFilename());
			if (!destination.isDirectory()) {
				FileUtils.copyInputStreamToFile(in, destination);
				logger.info(destination.getAbsolutePath());
			}
			logger.info(ruta);
		//	logger.info("Destino"+destination);
			//guardo dentro de curso ----  temario
			curso.setTemario(file.getOriginalFilename());
			if(curso.getCodigo() > Curso.CODIGO_NULO){
				logger.info("AQUI update:"+curso.toString());
				try{
					cS.update(curso);
					logger.info(curso.getProfesor().toString());
					txt ="El curso se ha actualizado correctamente";
					mensaje =new Mensaje(MensajeType.MSG_TYPE_SUCCESS);
				}catch(Exception e){
					logger.error("Se ha lanzado una excepcion en update");
					mensaje = new Mensaje(MensajeType.MSG_TYPE_DANGER);
					txt = "Ha habido problemas en la update";
				}
				
			}else{
				logger.info("Objeto create:"+curso.toString());
				
				logger.info(curso.getAlumnos().toString());
				try{
					cS.create(curso);
					txt ="El curso se ha creado correctamente";
					mensaje =new Mensaje(MensajeType.MSG_TYPE_SUCCESS);
					logger.info("mensaje"+mensaje);
				}catch (Exception e){
					
					logger.error("Se ha lanzado una excepcion en create");
					mensaje = new Mensaje(MensajeType.MSG_TYPE_DANGER);
					txt = "Ha habido problemas en la creación";
				}
			}
			mensaje.setMsg(txt);
			redirectMap.addFlashAttribute("mensaje",mensaje);
		}
		return destino;
	}
	
	@RequestMapping(value = "/deleteCurso/{codigo}")
	public String delete(@PathVariable("codigo") int codigo) {
		cS.delete(codigo);
		return "redirect:/cursos";
	}
	
	@RequestMapping("/editCurso/{codigo}")
	public String editCurso(@PathVariable("codigo") long codigo, Model model) {
		Curso cur = cS.getById(codigo);
		List<Profesor> profesores = pS.getAll();
		List<Cliente> clientes = cliS.getAll();
		List<Alumno> alumnos = aS.getAll();
		logger.info(codigo + " " + cur.toString());
		model.addAttribute("curso", cur);
		model.addAttribute("listadoProfesores", profesores);
		model.addAttribute("listadoClientes", clientes);
		model.addAttribute("listadoAlumnos", alumnos);
		return "cursoform";

	}
	
	/*@RequestMapping(value = "/editCurso/{codigocurso}")
	public ModelAndView editCurso(@PathVariable("codigocurso") long codigocurso) {
		mav = new ModelAndView("/cursos/cursoform");
		Curso curso = cS.getById(codigocurso);
		logger.info(curso.toString());
		mav.addObject("curso", curso);
		List<Profesor> profesores = pS.getAll();
		mav.addObject("listadoProfesores", profesores);
		return mav;
	}*/
	/*
	@RequestMapping(value = "/informe/{codigo}")
	public String verInforme(@PathVariable("codigo") int codigo,Model model) {
		logger.info("informe Curso");
		model.addAttribute("curso", cS.getById(codigo));
		return "cursos/curso";
		
	}
	*/
	
}
