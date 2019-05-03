package net.unibave.npa.core.persistence.model;

import net.unibave.npa.core.persistence.enumeration.BeanType;
import net.unibave.npa.core.persistence.enumeration.EntityBeanStatus;
import net.unibave.npa.core.util.reflect.abstracts.IInjector;
import net.unibave.npa.core.util.reflect.metainf.Factory;
import net.unibave.npa.core.util.reflect.metainf.Property;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

/**
 *
 * @author wesley
 */
public class EntityBean implements Serializable {

    private Class<?> entityClass;
    private Object entityInstance;
    private String name;
    private String comment;
    private String dataBase;
    private EntityKeyBean entityKey;
    private List<ControllerBean> controllers;
    private List<ValidatorBean> validators;
    private List<AttributeBean> attributes;
    private EntityBeanStatus status;
    private BeanType beanType;

    protected EntityBean() {
        super();
        this.status = EntityBeanStatus.NEW;
    }

    protected EntityBean(Class<?> entityClass, Object entityInstance, String name, String comment, String dataBase,
                         EntityKeyBean entityKey, List<ControllerBean> controllers, List<ValidatorBean> validators,
                         List<AttributeBean> attributes, EntityBeanStatus status, BeanType beanType) {
        super();
        this.entityClass = entityClass;
        this.entityInstance = entityInstance;
        this.name = name;
        this.comment = comment;
        this.dataBase = dataBase;
        this.entityKey = entityKey;
        this.controllers = controllers;
        this.validators = validators;
        this.attributes = attributes;
        this.status = status;
        this.beanType = beanType;
    }

    @Override
    public String toString() {
        return dataBase + "." + name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((entityClass == null) ? 0 : entityClass.hashCode());
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
        if (!(obj instanceof EntityBean)) {
            return false;
        }
        EntityBean other = (EntityBean) obj;
        if (entityClass == null) {
            if (other.entityClass != null) {
                return false;
            }
        } else if (!entityClass.equals(other.entityClass)) {
            return false;
        }
        return true;
    }

    public Class<?> getEntityClass() {
        return entityClass;
    }

    protected void setEntityClass(Class<?> entityClass) {
        this.entityClass = entityClass;
    }

    public Object getEntityInstance() {
        return entityInstance;
    }

    protected void setEntityInstance(Object entityInstance) {
        this.entityInstance = entityInstance;
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

    public String getDataBase() {
        return dataBase;
    }

    protected void setDataBase(String dataBase) {
        this.dataBase = dataBase;
    }

    public EntityKeyBean getEntityKey() {
        return entityKey;
    }

    protected void setEntityKey(EntityKeyBean entityKey) {
        this.entityKey = entityKey;
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

    public List<AttributeBean> getAttributes() {
        return attributes;
    }

    protected void setAttributes(List<AttributeBean> attributes) {
        this.attributes = attributes;
    }

    public EntityBeanStatus getStatus() {
        return status;
    }

    public void setStatus(EntityBeanStatus status) {
        this.status = status;
    }

    private static final long serialVersionUID = -6575429404007227774L;

    public Object getKeyValue() throws Exception {
        return this.getEntityKey().getAttribute().getValue();
    }

    public BeanType getBeanType() {
        return beanType;
    }

    public void setBeanType(BeanType beanType) {
        this.beanType = beanType;
    }
}
