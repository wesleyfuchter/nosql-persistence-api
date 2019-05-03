package net.unibave.npa.core.persistence.impl.validator;

import net.unibave.npa.core.persistence.abstracts.AbstractValidator;
import net.unibave.npa.core.persistence.metainf.MaxLength;
import net.unibave.npa.core.persistence.metainf.MinLength;

import java.lang.reflect.Field;

public class MaxLengthValidatorImpl extends AbstractValidator<Object, Object> {

    @Override
    public Boolean validateEntity(Object o) throws Exception {
        return true;
    }

    @Override
    public Boolean validateField(Object o, Object fieldValue) throws Exception {
        return fieldValue.toString().length() <= ((Field) o).getAnnotation(MaxLength.class).value();
    }

}
