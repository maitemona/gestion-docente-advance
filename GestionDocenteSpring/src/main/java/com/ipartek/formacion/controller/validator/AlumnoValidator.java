package com.ipartek.formacion.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ipartek.formacion.dbms.persistence.Alumno;
import com.ipartek.formacion.service.Util;

public class AlumnoValidator implements Validator{
/*
 *que clases deberia procesar este validador
 */
	@Override
	public boolean supports(Class<?> paramClass) {
		
		return Alumno.class.equals(paramClass);
	}

	/*new Object[]{ "'codigo'" } es unn array donde guarda tod lo que voy poniendo por codigo*/
	
	@Override
	public void validate(Object obj, Errors errors) {
		
		/*Aqui solo llegan objetos alumnos por la funcion de arriba*/
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre","505","Error, nombre no puede estar vacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apellidos","505","Error, apellidos no puede estar vacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni","505","Error, dni no puede estar vacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email","505","Error, email no puede estar vacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefono","505","Error, telefono no puede estar vacio");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "direccion","505","Error, direccion no puede estar vacio");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "poblacion","505","Error, poblacion no puede estar vacio");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codigopostal","505","Error, codigo postal no puede estar vacio");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nHermanos","505","Error, nº de hermanos no puede estar vacio");
	//castin del abjeto alumno
		Alumno alum = (Alumno) obj;
		
		if(alum.getCodigo()< Alumno.CODIGO_NULO){
			errors.rejectValue("codigo","valorNegativo",new Object[]{ "'codigo'" },"no se puede "+ Alumno.CODIGO_NULO);
		}
		
		//Util.validarDni(alum.getDni());
		if(Util.validarDni(alum.getDni())==false){
			errors.rejectValue("dni","letraDniIncorrecto",new Object[]{ "'dni'" },"no es valido el DNI");
		}
	}

}
