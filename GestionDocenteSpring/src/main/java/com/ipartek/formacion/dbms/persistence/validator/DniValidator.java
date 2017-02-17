package com.ipartek.formacion.dbms.persistence.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.ipartek.formacion.dbms.persistence.Profesor;
import com.ipartek.formacion.service.interfaces.ProfesorService;

public class DniValidator implements ConstraintValidator<Dni, Object> {

	 private String profesorcodigo;
	 private String profesorKey;
	private static final Logger logger = LoggerFactory.getLogger(DniValidator.class);
	@Autowired
	ProfesorService pS;
	@Override
	public void initialize(Dni a) {
		
		this.profesorcodigo = a.profesorcodigo();
		this.profesorKey = a.profesorKey();
	
	}
	
	
	@Override
	public boolean isValid( Object object,ConstraintValidatorContext cvc) {
		
		boolean valido=true;
		logger.info(object.toString());
		// final Profesor dniN;
	   //  final Profesor codigoN;
	     try {
	    	
		    	 final String codigoN = BeanUtils.getProperty(object, profesorcodigo);
		    	
		    	 final String dniN = BeanUtils.getProperty(object, profesorKey);
		    	 
		    	 logger.info("Que tengo aqui:"+ BeanUtils.getProperty(object, profesorKey));
		    	 logger.info("dniN:"+dniN);
		    	 
		    	 logger.info("codigoN:"+codigoN);
		    	 
		    	 Object obj = null;
		    	 
		    	 logger.info("Que es:"+("dni".equalsIgnoreCase(profesorKey)));
		    	 
		    	 	if ("dni".equalsIgnoreCase(profesorKey)) {
						obj = pS.getByDni(dniN);
						logger.info("Que tengo aqui get:"+ pS.getByDni(dniN));
		    	 	}
	    	
	        } catch (final Exception e) {
	        	valido = false;
	        }
	  
	     logger.info("valido:"+ valido); 
		return valido;
	}











	
	
}
