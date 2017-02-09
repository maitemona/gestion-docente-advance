package com.ipartek.formacion.dbms.persistence.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
/*Documentamos la clase validator de phone, le decimo que(@Constrait),a que($target)
  y cuando (@runtime)*/

@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Target({ElementType.METHOD ,ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {

	  String message() default "{Phone}";
	  Class<?>[] groups() default {};
	  Class<? extends Payload>[] payload() default {};
}
