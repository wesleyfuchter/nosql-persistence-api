package net.unibave.npa.core.persistence.model;

import net.unibave.npa.core.persistence.enumeration.BeanType;
import net.unibave.npa.core.persistence.exceptions.InvalidEntityPersistenceRuntimeException;
import net.unibave.npa.core.persistence.metainf.EmbeddedEntity;
import net.unibave.npa.core.persistence.metainf.Entity;

import java.util.List;

/**
 * Created by wesley on 31/07/16.
 */
public abstract class AbstractEntityFactory<E> extends AbstractFactory<E,EntityBean> {

    @Override
    public final EntityBean factory(E argument) throws Exception {
        setArgument(argument);
        final EntityBean entityBean = new EntityBean();
        setObjectFactory(entityBean);
        entityBean.setEntityInstance(getEntityInstance());
        entityBean.setEntityClass(getEntityClass());
        entityBean.setName(getName());
        entityBean.setComment(getComment());
        entityBean.setDataBase(getDataBase());
        entityBean.setEntityKey(getEntityKeyBean());
        entityBean.setControllers(getControllers());
        entityBean.setValidators(getValidators());
        entityBean.setAttributes(getAttributes());
        entityBean.setBeanType(getBeanType());
        return entityBean;
    }

    @Override
    protected final Entity getRepresentativeAnnotation() {
        if (getEntityClass().isAnnotationPresent(Entity.class)) {
            return getEntityClass().getAnnotation(Entity.class);
        } else if (getEntityClass().isAnnotationPresent(EmbeddedEntity.class)) {
            if (getEntityClass().getAnnotation(EmbeddedEntity.class).principalEntityClass().isAnnotationPresent(Entity.class)) {
                return getEntityClass().getAnnotation(EmbeddedEntity.class).principalEntityClass().getAnnotation(Entity.class);
            }
        }
        throw new InvalidEntityPersistenceRuntimeException(InvalidEntityPersistenceRuntimeException.DEFAULT_ERR_MESSAGE);
    }

    protected abstract Object getEntityInstance() throws Exception;

    protected abstract Class<?> getEntityClass();

    protected abstract String getName();

    protected abstract String getComment();

    protected abstract String getDataBase();

    protected abstract EntityKeyBean getEntityKeyBean() throws Exception;

    protected abstract List<ControllerBean> getControllers() throws Exception;

    protected abstract List<ValidatorBean> getValidators() throws Exception;

    protected abstract List<AttributeBean> getAttributes() throws Exception;

    protected abstract BeanType getBeanType();

}
