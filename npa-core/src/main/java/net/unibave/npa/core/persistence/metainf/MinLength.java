package net.unibave.npa.core.persistence.metainf;

import net.unibave.npa.core.persistence.impl.validator.MinLengthValidatorImpl;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
@Validator(implementClass = MinLengthValidatorImpl.class, message = "The field must have a min length")
public @interface MinLength {
    int value();
}
