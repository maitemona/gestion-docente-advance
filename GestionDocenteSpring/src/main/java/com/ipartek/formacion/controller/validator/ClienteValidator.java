package com.ipartek.formacion.controller.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ipartek.formacion.controller.ClienteController;
import com.ipartek.formacion.dbms.persistence.Alumno;
import com.ipartek.formacion.dbms.persistence.Cliente;
import com.ipartek.formacion.service.interfaces.ClienteService;

public class ClienteValidator implements Validator{
	
	private static final Logger logger = LoggerFactory.getLogger(ClienteValidator.class);
	@Autowired 
	ClienteService cS;
	@Override
	public boolean supports(Class<?> paramClass) {
		return Cliente.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre","form.nombreRequerido","Tiene que introducirse un nombre");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "identificador","505","Error, Identificador no puede estar vacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "form.emailRequerido","tiene que introducirse un email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefono", "form.telefonoRequerido","tiene que introducirse un telefono");
		
		
		
		
		Cliente clien = (Cliente) obj;
		
		if(clien.getCodigo()< Cliente.CODIGO_NULO){
			errors.rejectValue("codigo","valorNegativo",new Object[]{ "'codigo'" },"no se puede "+ Cliente.CODIGO_NULO);
		}
		if (clien.getNombre().length() < 3 || clien.getNombre().length() > 50) {
			errors.rejectValue("nombre", "form.longitudNombreIncorrecta", new Object[] { 3, 50 },
					"Nombre tiene que ocupar entre " + 3 + " y " + 50 + " caracteres.");
		}
		if (clien.getDireccion().length() >250 ) {
			errors.rejectValue("direccion", "form.longitudDireccionIncorrecta", new Object[] { "direccion" },
					"Direccion no puede ser mas de 250 caracteres ");
		}
		if (clien.getTelefono().length() >9 ) {
			errors.rejectValue("telefono", "form.longitudTelefonoIncorrecta", new Object[] { "telefono" },
					"Telefono no puede ser mas de 9 numeros");
		}
		if (clien.getEmail().length() >150) {
			errors.rejectValue("email", "form.longitudEmailIncorrecta", new Object[] { "email" },
					"Identificador no puede ser mas de 150 caracteres");
		}
		if (clien.getIdentificador().length() >200) {
			errors.rejectValue("Identificador", "form.longitudIdentificdorIncorrecta", new Object[] { "identificador" },
					"Identificador no puede ser mas de 200 caracteres");
		}
		
		if(clien.getCodigo() == Cliente.CODIGO_NULO){
			if(cS.getByDni(clien.getIdentificador())!=null){
				logger.info("CODIGO"+clien.getCodigo());
				errors.rejectValue("Identificador","form.identificadorExiste", new Object[] { "identificador" },
					"DNI/Nº SS no valido, ya exite en BBDD");
			}
		}
		
	}

}
