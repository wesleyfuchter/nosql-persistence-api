package net.unibave.npa.core.persistence.model;

import net.unibave.npa.core.persistence.abstracts.AbstractValidator;
import net.unibave.npa.core.persistence.exceptions.ValidatorException;
import net.unibave.npa.core.persistence.metainf.Validator;
import net.unibave.npa.core.util.reflect.ReflectionLookupFacade;

import java.lang.annotation.Annotation;

/**
 * Created by wesley on 31/07/16.
 */
public final class SpecificValidatorFactory extends AbstractValidatorFactory<Annotation> {

    @Override
    protected Annotation getRepresentativeAnnotation() {
        return getArgument();
    }

    @Override
    protected Class<? extends Annotation> getAnnotationClass() {
        return getArgument().annotationType();
    }

    @Override
    protected Integer getSequence() {
        return getArgument().annotationType().getAnnotation(Validator.class).sequence();
    }

    @Override
    protected AbstractValidator getValidatorInstance() throws Exception {
        return ReflectionLookupFacade.getInstance().<AbstractValidator>createTypedInstance(getArgument().annotationType().getAnnotation(Validator.class).implementClass());
    }

    @Override
    protected Class<? extends AbstractValidator<?,?>> getImplementClass() {
        return getArgument().annotationType().getAnnotation(Validator.class).implementClass();
    }

    @Override
    protected Class<? extends ValidatorException> getExceptionToThrow() {
        return getArgument().annotationType().getAnnotation(Validator.class).exceptionToThrowClass();
    }

    @Override
    protected String getMessage() {
        return getArgument().annotationType().getAnnotation(Validator.class).message();
    }

}
