package com.qbryx.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Documented
@Constraint(validatedBy = {})
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
@NotEmpty
@Pattern(regexp = "[0-9]+")
@Size(min=12, max=12)
public @interface ValidateUpc {

    public abstract String message() default "UPC cannot be empty, should contain numbers only and be 12 characters long";

    public abstract Class<?>[] groups() default {};

    public abstract Class<?>[] payload() default {};
}