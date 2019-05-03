package net.unibave.npa.core.persistence.model;

import net.unibave.npa.core.persistence.abstracts.AbstractController;
import net.unibave.npa.core.persistence.abstracts.AbstractValidator;
import net.unibave.npa.core.persistence.exceptions.ValidatorException;
import net.unibave.npa.core.persistence.metainf.Validator;
import net.unibave.npa.core.util.reflect.ReflectionLookupFacade;

import java.lang.annotation.Annotation;

/**
 * Created by wesley on 31/07/16.
 */
public final class GenericValidatorFactory extends AbstractValidatorFactory<Validator> {

    @Override
    protected Validator getRepresentativeAnnotation() {
        return getArgument();
    }

    @Override
    protected Class<? extends Annotation> getAnnotationClass() {
        return getArgument().annotationType();
    }

    @Override
    protected Integer getSequence() {
        return getArgument().sequence();
    }

    @Override
    protected AbstractValidator getValidatorInstance() throws Exception {
        return ReflectionLookupFacade.getInstance().<AbstractValidator>createTypedInstance(getArgument().implementClass());
    }

    @Override
    protected Class<? extends AbstractValidator<?,?>> getImplementClass() {
        return getArgument().implementClass();
    }

    @Override
    protected Class<? extends ValidatorException> getExceptionToThrow() {
        return getRepresentativeAnnotation().exceptionToThrowClass();
    }

    @Override
    protected String getMessage() {
        return getRepresentativeAnnotation().message();
    }
}
