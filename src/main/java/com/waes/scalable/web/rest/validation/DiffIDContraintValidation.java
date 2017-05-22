package com.waes.scalable.web.rest.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.waes.scalable.web.diff.api.IDiffManager;


/**
 * The Class DiffIDContraintValidation.
 */
public class DiffIDContraintValidation implements ConstraintValidator<DiffID, String> {

	/** The facade. */
	@Autowired
	private IDiffManager facade;
	
	/* (non-Javadoc)
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	@Override
	public void initialize(DiffID arg0) {
		
	}

	/* (non-Javadoc)
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object, javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(String id, ConstraintValidatorContext context) {
		
		if(!facade.isValidDiff(id)) {
			context.disableDefaultConstraintViolation();
	        context
	            .buildConstraintViolationWithTemplate(String.format("Diff id: %s is not valid", id))
	            .addConstraintViolation();
	        return false;
		}
		return true;
	}
}
