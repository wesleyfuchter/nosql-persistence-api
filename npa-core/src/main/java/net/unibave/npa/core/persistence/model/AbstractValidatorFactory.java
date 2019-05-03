package net.unibave.npa.core.persistence.model;

import net.unibave.npa.core.persistence.abstracts.AbstractController;
import net.unibave.npa.core.persistence.abstracts.AbstractValidator;
import net.unibave.npa.core.persistence.exceptions.ValidatorException;

import java.lang.annotation.Annotation;

/**
 * Created by wesley on 31/07/16.
 */
public abstract class AbstractValidatorFactory<E> extends AbstractFactory<E,ValidatorBean> {

    @Override
    protected Annotation getRepresentativeAnnotation() {
        return null;
    }

    @Override
    public final ValidatorBean factory(E argument) throws Exception {
        setArgument(argument);
        final ValidatorBean validatorBean = new ValidatorBean();
        validatorBean.setAnnotationClass(getAnnotationClass());
        validatorBean.setSequence(getSequence());
        validatorBean.setValidatorInstance(getValidatorInstance());
        validatorBean.setImplementClass(getImplementClass());
        validatorBean.setExceptionToThrow(getExceptionToThrow());
        validatorBean.setMessage(getMessage());
        return validatorBean;
    }

    protected abstract Class<? extends Annotation> getAnnotationClass();

    protected abstract Integer getSequence();

    protected abstract AbstractValidator getValidatorInstance() throws Exception;

    protected abstract Class<? extends AbstractValidator<?,?>> getImplementClass();

    protected abstract Class<? extends ValidatorException> getExceptionToThrow();

    protected abstract String getMessage();

}
