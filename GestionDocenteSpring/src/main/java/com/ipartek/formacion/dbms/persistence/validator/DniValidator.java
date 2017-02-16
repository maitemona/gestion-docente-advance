package com.ipartek.formacion.dbms.persistence.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.ipartek.formacion.dbms.persistence.Profesor;
import com.ipartek.formacion.service.interfaces.ProfesorService;

public class DniValidator implements ConstraintValidator<Dni, Profesor> {

	private static final Logger logger = LoggerFactory.getLogger(DniValidator.class);
	@Autowired
	ProfesorService pS;
	@Override
	public void initialize(Dni param) {

		
	}
	
	

	public boolean viejo(String dniN, ConstraintValidatorContext ctx) {
		boolean valido=true;
		Profesor profesor = pS.getByDni(dniN);
		if(profesor==null){
			valido = false;
		}
		
		return valido;
	}
	
	
	/*public boolean isValid(Profesor value, ConstraintValidatorContext context) {
	        
	        Profesor profe = (Profesor) value;
	        
	    	if(pS.getByDni(profe.getDni())==null ||profe.getCodigo()== profe.CODIGO_NULO)
	    	{
	    	 	logger.info("Mi variable dniN:" + pS.getByDni(profe.getDni()));
	    		return true;
	    	
	    	}
    	 	
	    	return false;
	    }
*/

	@Override
	public boolean isValid(Profesor value, ConstraintValidatorContext context) {
		 final Profesor dniN;
	     final Profesor codigoN;
	     try {
	    	 	dniN = pS.getByDni(value.getDni());
	    	 	logger.info("Mi variable dniN:" + dniN);
	    	 	codigoN = pS.getById(value.getCodigo());
	    	 	logger.info("Mi variable codigo:" + codigoN);
	            /* Validation logic goes here */
	        } catch (final Exception e) {
	            throw new RuntimeException(e.getMessage(), e);
	        }
	     if (dniN == null || codigoN == null) { return true; }
	     
	        // return false, just to make it always fail
	       
		return false;
	}


	
	
}
