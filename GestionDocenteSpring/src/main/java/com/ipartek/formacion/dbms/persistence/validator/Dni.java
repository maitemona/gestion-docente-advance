package com.ipartek.formacion.dbms.persistence.validator;
/*Documentamos la clase validator de DniUnico, le decimo que(@Constrait),a que($target)
y cuando (@runtime)*/

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = DniValidator.class)
@Target({ElementType.METHOD ,ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Dni {

	  String message() default "{Dni}";
	  Class<?>[] groups() default {};
	  Class<? extends Payload>[] payload() default {};
	  String profesorcodigo();
	  String profesorKey();
	 
	    @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
	    @Retention(RetentionPolicy.RUNTIME)
	    @Documented
	    @interface List {
	    	Dni[] value();
	    }
}
