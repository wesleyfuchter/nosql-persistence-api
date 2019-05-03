package net.unibave.npa.core.persistence.model;

import net.unibave.npa.core.persistence.abstracts.IConverter;
import net.unibave.npa.core.persistence.exceptions.InvalidAttributePersistenceRuntimeException;
import net.unibave.npa.core.persistence.metainf.Attribute;

import java.lang.reflect.Field;
import java.util.List;

public abstract class AbstractAttributeFactory<E> extends AbstractFactory<E,AttributeBean> {

    @Override
    public final AttributeBean factory(E argument) throws Exception {
        setArgument(argument);
        final AttributeBean attributeBean = new AttributeBean();
        attributeBean.setName(getName());
        attributeBean.setComment(getComment());
        attributeBean.setEntityBean(getEntityBean());
        attributeBean.setField(getField());
        attributeBean.setValue(getValue());
        attributeBean.setPrompt(getPrompt());
        attributeBean.setControllers(getControllers());
        attributeBean.setValidators(getValidators());
        attributeBean.setConverter(getConverter());
        return attributeBean;
    }

    @Override
    protected final Attribute getRepresentativeAnnotation() {
        if (getField().isAnnotationPresent(Attribute.class)) {
            return getField().getAnnotation(Attribute.class);
        }
        throw new InvalidAttributePersistenceRuntimeException(InvalidAttributePersistenceRuntimeException.DEFAULT_ERR_MESSAGE);
    }

    protected abstract String getName();

    protected abstract String getComment();

    protected abstract Field getField();

    protected abstract String getPrompt();

    protected abstract List<ControllerBean> getControllers() throws Exception;

    protected abstract List<ValidatorBean> getValidators() throws Exception;

    protected abstract EntityBean getEntityBean();

    protected abstract IConverter getConverter() throws Exception;

    protected abstract Object getValue() throws Exception;

}

