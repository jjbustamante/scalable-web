package com.waes.scalable.web.rest.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.waes.scalable.web.diff.api.IDiffManager;

public class DiffIDContraintValidation implements ConstraintValidator<DiffID, String> {

	@Autowired
	private IDiffManager facade;
	
	@Override
	public void initialize(DiffID arg0) {
		
	}

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
