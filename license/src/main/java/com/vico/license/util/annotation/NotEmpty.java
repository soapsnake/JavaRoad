package com.vico.license.util.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.vico.license.util.notEmptyValidator;

@Documented
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = notEmptyValidator.class)
public @interface NotEmpty {
	
	String message() default "not_empty";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
	
	
}
