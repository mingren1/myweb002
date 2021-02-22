package com.athome.mygmall.common.validator.hiber.anno;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.athome.mygmall.common.validator.hiber.SexConstraintValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 性别约束
 */
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SexConstraintValidator.class)
public @interface Sex {

    String message() default "性别有误";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
