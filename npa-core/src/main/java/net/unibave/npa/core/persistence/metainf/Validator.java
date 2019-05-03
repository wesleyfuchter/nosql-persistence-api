/**
 *
 */
package net.unibave.npa.core.persistence.metainf;

import net.unibave.npa.core.persistence.abstracts.AbstractValidator;
import net.unibave.npa.core.persistence.exceptions.ValidatorException;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to set metainf on validator entity class
 *
 * @author wesley
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE, ElementType.FIELD})
public @interface Validator {

    /**
     * AbstractValidator implement class
     *
     * @author wesley
     */
    Class<? extends AbstractValidator<?, ?>> implementClass();

    /**
     * Sequence of execution
     */
    int sequence() default 0;

    /**
     * Represents the exception to throw on validate fail
     */
    Class<? extends ValidatorException> exceptionToThrowClass() default ValidatorException.class;

    /**
     * Represents the message to show on validate fail
     */
    String message() default "";

}
