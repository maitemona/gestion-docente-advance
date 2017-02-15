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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre","form.nombreRequerido","Tiene que introducirse un nombre");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apellidos", "form.apellidoRequerido", "tiene que introducirse un apellido");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "form.emailRequerido","tiene que introducirse un email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "form.dniRequerido", "tiene que introducirse un dni");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefono", "form.telefonoRequerido","tiene que introducirse un telefono");
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
			errors.rejectValue("dni","Pattern.dni",new Object[]{ "'dni'" },"no es valido el DNI");
		}
		
		if (alum.getNombre().length() < 3 || alum.getNombre().length() > 50) {
			errors.rejectValue("nombre", "form.longitudNombreIncorrecta", new Object[] { 3, 50 },
					"Nombre tiene que ocupar entre " + 3 + " y " + 50 + " caracteres.");
		}
		if (alum.getApellidos().length() < 3 || alum.getApellidos().length() >250 ) {
			errors.rejectValue("apellidos", "form.longitudApellidosIncorrecta", new Object[] { "apellidos" },
					"Apellidos tiene que ocupar entre " + 3 + " y " + 250 + " caracteres.");
		}
		if (alum.getDireccion().length() >250 ) {
			errors.rejectValue("direccion", "form.longitudDireccionIncorrecta", new Object[] { "direccion" },
					"Direccion no puede ser mas de 250 caracteres ");
		}
		if (alum.getEmail().length() >150 ) {
			errors.rejectValue("email", "form.longitudEmailIncorrecta", new Object[] { "email" },
					"Email no puede ser mas de 150 caracteres ");
		}
		if (alum.getPoblacion().length() >150 ) {
			errors.rejectValue("direccion", "form.longitudPoblacionIncorrecta", new Object[] { "poblacion" },
					"Poblacion no puede ser mas de 150 caracteres ");
		}
		if (alum.getCodigo() >5 ) {
			errors.rejectValue("codigopostal", "form.longitudCodigoPostalIncorrecta", new Object[] { "codigopostal" },
					"Codigo Postal  no puede ser mas de 5 numeros ");
		}
		if (alum.getTelefono().length() >9 ) {
			errors.rejectValue("telefono", "form.longitudTelefonoIncorrecta", new Object[] { "telefono" },
					"Telefono no puede ser mas de 9 numeros");
		}
		
	}

}
