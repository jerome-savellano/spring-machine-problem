package com.qbryx.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Documented
@Constraint(validatedBy = {})
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
@NotNull
@Min(10) 
@Max(100)
public @interface ValidateStock {

    public abstract String message() default "Stock cannot be empty, can be any value between 10 and 100";

    public abstract Class<?>[] groups() default {};

    public abstract Class<?>[] payload() default {};
}
