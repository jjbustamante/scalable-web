package com.waes.scalable.web.rest.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=DiffIDContraintValidation.class)
public @interface DiffID {

	String message() default "Diff id";
	
	Class<?>[] groups() default {};
        
	Class<? extends Payload>[] payload() default {};
}
