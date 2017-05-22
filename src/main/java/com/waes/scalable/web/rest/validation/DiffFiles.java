package com.waes.scalable.web.rest.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=DiffFilesContraintValidation.class)
public @interface DiffFiles {

	String message() default "Diff files";
	
	Class<?>[] groups() default {};
        
	Class<? extends Payload>[] payload() default {};
}
