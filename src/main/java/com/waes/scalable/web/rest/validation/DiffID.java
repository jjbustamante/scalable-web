package com.waes.scalable.web.rest.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


/**
 * The Interface DiffID.
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=DiffIDContraintValidation.class)
public @interface DiffID {

	/**
	 * Message.
	 *
	 * @return the string
	 */
	String message() default "Diff id";
	
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
