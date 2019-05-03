package net.unibave.npa.core.persistence.metainf;

import net.unibave.npa.core.persistence.impl.validator.NotNullValidatorImpl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
@Validator(implementClass = NotNullValidatorImpl.class, message = "The field is required")
public @interface NotNull {}
