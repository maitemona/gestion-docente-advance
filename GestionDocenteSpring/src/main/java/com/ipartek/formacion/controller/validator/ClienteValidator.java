package com.ipartek.formacion.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


import com.ipartek.formacion.dbms.persistence.Cliente;

public class ClienteValidator implements Validator{

	@Override
	public boolean supports(Class<?> paramClass) {
		return Cliente.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre","505","Error, Nombre no puede estar vacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "identificador","505","Error, Identificador no puede estar vacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email","505","Error, Email no puede estar vacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefono","505","Error, Telefono no puede estar vacio");
		
		Cliente clien = (Cliente) obj;
		
		if(clien.getCodigo()< Cliente.CODIGO_NULO){
			errors.rejectValue("codigo","valorNegativo",new Object[]{ "'codigo'" },"no se puede "+ Cliente.CODIGO_NULO);
		}
		
		
	}

}
