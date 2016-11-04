package com.qbryx.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Documented
@Constraint(validatedBy = {})
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
@Min(value=1)
@Max(value=Integer.MAX_VALUE)
@Digits(integer=8, fraction=2)
@NotNull
public @interface ValidatePrice {

    public abstract String message() default "Price cannot be empty, must be greater than 0, and has two decimal place max.";

    public abstract Class<?>[] groups() default {};

    public abstract Class<?>[] payload() default {};
}