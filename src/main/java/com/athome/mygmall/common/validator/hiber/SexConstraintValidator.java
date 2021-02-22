package com.athome.mygmall.common.validator.hiber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.athome.mygmall.common.validator.hiber.anno.Sex;

/**
 * 性别约束逻辑判断
 */
public class SexConstraintValidator implements ConstraintValidator<Sex, String> {

	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value != null && (value.equals("男") || value.equals("女"));
	}

	public void initialize(Sex constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}
}
