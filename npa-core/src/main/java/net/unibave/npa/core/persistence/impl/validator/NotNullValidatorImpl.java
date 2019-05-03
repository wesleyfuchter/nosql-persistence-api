package net.unibave.npa.core.persistence.impl.validator;

import net.unibave.npa.core.persistence.abstracts.AbstractValidator;
import net.unibave.npa.core.persistence.metainf.NotNull;

import java.util.Objects;

public class NotNullValidatorImpl extends AbstractValidator<Object, Object> {

    @Override
    public Boolean validateEntity(Object o) throws Exception {
        return true;
    }

    @Override
    public Boolean validateField(Object o, Object fieldValue) throws Exception {
        return Objects.nonNull(fieldValue);
    }
}
