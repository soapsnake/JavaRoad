package com.vico.license.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.github.pagehelper.StringUtil;
import com.vico.license.util.annotation.NotEmpty;

public class notEmptyValidator implements ConstraintValidator<NotEmpty, String> {
	
	@Override
	public void initialize(NotEmpty constraintAnnotation){}
	
	@Override
	public boolean isValid(String value,ConstraintValidatorContext context){
		return StringUtil.isNotEmpty(value);
	}
}
