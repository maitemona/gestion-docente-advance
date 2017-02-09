package com.ipartek.formacion.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
	
}
