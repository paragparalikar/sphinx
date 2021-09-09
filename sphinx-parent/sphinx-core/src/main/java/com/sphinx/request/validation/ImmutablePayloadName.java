package com.sphinx.request.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = ImmutablePayloadNameValidator.class)
@Target( { ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE_PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ImmutablePayloadName {
	String message() default "Name is immutable";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
