/**
 *
 */
package net.unibave.npa.core.persistence.model;

import net.unibave.npa.core.persistence.abstracts.AbstractValidator;
import net.unibave.npa.core.persistence.exceptions.ValidatorException;
import net.unibave.npa.core.persistence.metainf.Controller;
import net.unibave.npa.core.persistence.metainf.Validator;
import net.unibave.npa.core.util.reflect.ReflectionLookupFacade;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @author wesley
 */
public class ValidatorBean implements Serializable {

    private static final long serialVersionUID = 8706592506796934313L;

    private Class<? extends Annotation> annotationClass;
    private Integer sequence;
    private AbstractValidator<?, ?> validatorInstance;
    private Class<? extends AbstractValidator<?, ?>> implementClass;
    private Class<? extends ValidatorException> exceptionToThrow;
    private String message;

    protected ValidatorBean() {
        super();
    }

    protected ValidatorBean(Class<? extends Annotation> annotationClass, Integer sequence,
                            AbstractValidator<?, ?> validatorInstance, Class<? extends AbstractValidator<?, ?>> implementClass,
                            Class<? extends ValidatorException> exceptionToThrow, String message) {
        super();
        this.annotationClass = annotationClass;
        this.sequence = sequence;
        this.validatorInstance = validatorInstance;
        this.implementClass = implementClass;
        this.exceptionToThrow = exceptionToThrow;
        this.message = message;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((annotationClass == null) ? 0 : annotationClass.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ValidatorBean)) {
            return false;
        }
        ValidatorBean other = (ValidatorBean) obj;
        if (annotationClass == null) {
            if (other.annotationClass != null) {
                return false;
            }
        } else if (!annotationClass.equals(other.annotationClass)) {
            return false;
        }
        return true;
    }

    public Class<? extends Annotation> getAnnotationClass() {
        return annotationClass;
    }

    protected void setAnnotationClass(Class<? extends Annotation> annotationClass) {
        this.annotationClass = annotationClass;
    }

    public Integer getSequence() {
        return sequence;
    }

    protected void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public AbstractValidator<?, ?> getValidatorInstance() {
        return validatorInstance;
    }

    protected void setValidatorInstance(AbstractValidator<?, ?> validatorInstance) {
        this.validatorInstance = validatorInstance;
    }

    public Class<? extends AbstractValidator<?, ?>> getImplementClass() {
        return implementClass;
    }

    protected void setImplementClass(Class<? extends AbstractValidator<?, ?>> implementClass) {
        this.implementClass = implementClass;
    }

    public Class<? extends ValidatorException> getExceptionToThrow() {
        return exceptionToThrow;
    }

    protected void setExceptionToThrow(Class<? extends ValidatorException> exceptionToThrow) {
        this.exceptionToThrow = exceptionToThrow;
    }

    public String getMessage() {
        return message;
    }

    protected void setMessage(String message) {
        this.message = message;
    }

    public void validateEntity(EntityBean entityBean, SessionBean sessionBean) throws Exception {
        getValidatorInstance().setSessionBean(sessionBean);
        throwException(getValidatorInstance().validateObject(entityBean.getEntityInstance()),entityBean.getClass().getName());
    }

    public void validateField(EntityBean entityBean, AttributeBean attributeBean, SessionBean sessionBean) throws Exception {
        getValidatorInstance().setSessionBean(sessionBean);
        throwException(getValidatorInstance().validateFieldObject(attributeBean.getField(), attributeBean.getValue()),attributeBean.getField().getName());
    }

    private void throwException(Boolean validateValue, String name) throws Exception {
        if (!validateValue) {
            ValidatorException validatorException;
            if (!Objects.isNull(message) && !message.isEmpty()) {
                message = "Exception on "+name+" - " + message;
                validatorException = (ValidatorException) ReflectionLookupFacade.getInstance().createInstance(exceptionToThrow, message);
            } else {
                message = "Exception on "+name;
                validatorException = (ValidatorException) ReflectionLookupFacade.getInstance().createInstance(exceptionToThrow, message);
            }
            throw validatorException;
        }
    }

    public static Boolean isValidator(Annotation annotation) {
        return annotation.annotationType().isAnnotationPresent(Validator.class);
    }

}
