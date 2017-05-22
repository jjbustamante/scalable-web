package com.waes.scalable.web.rest.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.waes.scalable.web.rest.dto.FileUploadForm;

public class DiffFilesContraintValidation implements ConstraintValidator<DiffFiles, FileUploadForm> {

	@Override
	public void initialize(DiffFiles arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(FileUploadForm form, ConstraintValidatorContext context) {
		
		if(form.getFiles() == null || form.getFiles().size() < 2 || form.getFiles().size() > 2) {
			context.disableDefaultConstraintViolation();
	        context
	            .buildConstraintViolationWithTemplate(String.format("Diff operation accepts only two files"))
	            .addConstraintViolation();
	        return false;
		}
		return true;
	}
}
