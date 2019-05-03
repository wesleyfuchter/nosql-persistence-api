package net.unibave.npa.core.persistence.model;

import net.unibave.npa.core.persistence.abstracts.IConverter;
import net.unibave.npa.core.util.reflect.ReflectionLookupFacade;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author wesley
 */
public class AttributeBean implements Serializable {

    private String name;
    private String comment;
    private Field field;
    private String prompt;
    private List<ControllerBean> controllers;
    private List<ValidatorBean> validators;
    private EntityBean entityBean;
    private IConverter converter;

    protected AttributeBean() {
        super();
    }

    protected AttributeBean(String name,
                            String comment,
                            Field field,
                            String prompt,
                            List<ControllerBean> controllers,
                            List<ValidatorBean> validators,
                            EntityBean entityBean) {
        this.name = name;
        this.comment = comment;
        this.field = field;
        this.prompt = prompt;
        this.controllers = controllers;
        this.validators = validators;
        this.entityBean = entityBean;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        if (!(obj instanceof AttributeBean)) {
            return false;
        }
        AttributeBean other = (AttributeBean) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    protected void setComment(String comment) {
        this.comment = comment;
    }

    public Field getField() {
        return field;
    }

    protected void setField(Field field) {
        this.field = field;
    }

    public String getPrompt() {
        return prompt;
    }

    protected void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public Object getValue() throws Exception {
        final Object value = ReflectionLookupFacade.getInstance().getFieldValue(this.field,this.entityBean.getEntityInstance());
        final IConverter converter = getConverter();
        if (Objects.nonNull(converter)) {
            return converter.getAs(value);
        }
        return value;
    }

    public void setValue(Object value) throws Exception {
        final IConverter converter = getConverter();
        Object valueToSet = value;
        if (Objects.nonNull(converter)) {
            valueToSet = converter.setAs(value);
        }
        ReflectionLookupFacade.getInstance().setFieldValue(this.field,valueToSet,this.entityBean.getEntityInstance());
    }

    public List<ControllerBean> getControllers() {
        return controllers;
    }

    protected void setControllers(List<ControllerBean> controllers) {
        this.controllers = controllers;
    }

    public List<ValidatorBean> getValidators() {
        return validators;
    }

    protected void setValidators(List<ValidatorBean> validators) {
        this.validators = validators;
    }

    public EntityBean getEntityBean() {
        return entityBean;
    }

    protected void setEntityBean(EntityBean entityBean) {
        this.entityBean = entityBean;
    }

    public IConverter getConverter() {
        return converter;
    }

    public void setConverter(IConverter converter) {
        this.converter = converter;
    }

    private static final long serialVersionUID = 2980157326457789130L;

}
