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
import com.ipartek.formacion.dbms.persistence.Cliente;
import com.ipartek.formacion.service.interfaces.ClienteService;

@Controller
@RequestMapping(value = "/clientes")
public class ClienteController {

	/*busca una clase q lo implemente(@Autowired)*/
	@Inject
	private ClienteService cS;
	private static final Logger logger = LoggerFactory.getLogger(ProfesorController.class);
	ModelAndView mav = null;
	
	@Resource(name="clienteValidator")
	private Validator validator = null;
	/*metodo que alimente ese init que hemos hecho: estamos en SRIPNG*/
	@InitBinder
	private void initBinder(WebDataBinder binder){
		
		binder.setValidator(validator);
		/*el campo fecha es especiL, se valida asi*/
		/*binder.registerCustomEditor
		(Date.class,new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), false, 10));*/
	}
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAll() {
		logger.info("Controller lista size:");
		mav = new ModelAndView("/clientes/clientes");
		//Cargar a cargar lista de clientes
		List<Cliente> clientes = cS.getAll();
		//Engancharla  al modelandview
		logger.info("Controller lista size:"+clientes.size());
		mav.addObject("listadoClientes",clientes);
		logger.trace("pasa por getAll() de clientes");
		return mav; 
	}	
	
	/**
	 * Metodo para add un cliente
	 * la url es unica.
	 */
	@RequestMapping(value = "/addCliente")
	public String addCliente(Model model){
		model.addAttribute("cliente",new Cliente());
		return "/clientes/cliente";
	}
	
	@RequestMapping(value="/save",method =  RequestMethod.POST)
	public String saveCliente (Model model , @ModelAttribute("cliente") @Validated Cliente cliente, BindingResult bindingResult ){
		String destino ="";
		/*si las cosas estan mal nos mande de vuelta*/
		if(bindingResult.hasErrors()){
			logger.info("Cliente tiene errores");
			destino = "/clientes/cliente";
		}else{ 
			destino ="redirect:/clientes";
			if(cliente.getCodigo() > Cliente.CODIGO_NULO){
				logger.info(cliente.toString());
				cS.update(cliente);
			}else{
				logger.info(cliente.toString());
				cS.create(cliente);
			}
		}
		return destino;
	}
	
	@RequestMapping(value = "/{id}")
	public ModelAndView getByid(@PathVariable("id") int id){
		
		mav = new ModelAndView("/clientes/cliente");
		mav.addObject("cliente", cS.getById(id));
		return mav;
	}
	
	
	
	@RequestMapping(value = "/deleteCliente/{id}")
	public String deleteCliente(@PathVariable("id") int id){
		cS.delete(id);
		return "redirect:/clientes";
		
	}
}
