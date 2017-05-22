package com.waes.scalable.web.rest.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * The Interface DiffFiles.
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=DiffFilesContraintValidation.class)
public @interface DiffFiles {

	/**
	 * Message.
	 *
	 * @return the string
	 */
	String message() default "Diff files";
	
	/**
	 * Groups.
	 *
	 * @return the class[]
	 */
	Class<?>[] groups() default {};
        
	/**
	 * Payload.
	 *
	 * @return the class<? extends payload>[]
	 */
	Class<? extends Payload>[] payload() default {};
}
