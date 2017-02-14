package com.ipartek.formacion.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ipartek.formacion.controller.ProfesorController;

public class Util {
	
	private static final Logger logger = LoggerFactory.getLogger(Util.class);
	private Util() {
	}
	
	/*Telefono valiodar*/
	/*
	public static boolean validarTelefono(final String telefono) {
		boolean valido = false;
		final String REGEX = "[0-9]{9}";

		if (checkRegex(REGEX, telefono)) {
			valido = true;
		}
		return valido;
	}
	
	*/
	
	
	/**
	 * Validar si es Dni.
	 * @param le pasamos un strin dni.
	 * @return
	 * 			boolean true.
	 */
	
	
	public static boolean validarDni(String dni) {
		String letraMayuscula = ""; //Guardaremos la letra introducida en formato mayúscula	
	// Aquí excluimos cadenas distintas a 9 caracteres que debe tener un dni y también si el último caracter no es una letra	
		 if(dni.length() != 9 || Character.isLetter(dni.charAt(8)) == false ) {
			 return false;
		 }
		 else{
			 
			 logger.info("DNi" +dni);
			// Al superar la primera restricción, la letra la pasamos a mayúscula
		    letraMayuscula = (dni.substring(8)).toUpperCase();
		   // return true;
		    // Por último validamos que sólo tengo 8 dígitos entre los 8 primeros caracteres y que la letra introducida es igual a la de la ecuación
	        // Llamamos a los métodos privados de la clase soloNumeros() y letraDNI()
	     
		    int miDNI = Integer.parseInt(dni.substring(0,8));
	        int resto = 0;
	        String miLetra = "";
	        String[] asignacionLetra = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};
	 
	        resto = miDNI % 23;
	        logger.info("RESTO:"+resto);
	        miLetra = asignacionLetra[resto];
	        logger.info("miLetra:"+miLetra);
	        
	        int i, j = 0;
			String numero = ""; // Es el número que se comprueba uno a uno por si hay alguna letra entre los 8 primeros dígitos
			String miDNI_n = ""; // Guardamos en una cadena los números para después calcular la letra
			 String[] unoNueve = {"0","1","2","3","4","5","6","7","8","9"};
	        
			 for(i = 0; i < dni.length() - 1; i++) {
	             numero = dni.substring(i, i+1);

	             for(j = 0; j < unoNueve.length; j++) {
	                 if(numero.equals(unoNueve[j])) {
	                     miDNI_n += unoNueve[j];
	                 }
	             }
	         }

			 logger.info("miDNI:"+miDNI_n);
			 logger.info("Miletra:"+miLetra);
			 logger.info("letraMayuscula:"+letraMayuscula);
		    if(miDNI_n.length() == 8 && miLetra.equals(letraMayuscula)) {
	            return true;
	        }
	        else {
	            return false;
	        }
		}
	}

}
