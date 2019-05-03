/**
 *
 */
package net.unibave.npa.core.persistence.abstracts;

import net.unibave.npa.core.persistence.model.SessionBean;
import net.unibave.npa.core.util.reflect.ReflectionLookupFacade;

import java.util.Objects;

/**
 * @author wesley
 */
public abstract class AbstractValidator<K, E> {

    public AbstractValidator() {
    }

    private Class<E> entityClass;
    private Class<K> keyClass;

    /**
     * @return the entityClass
     */
    public final Class<E> getEntityClass() {
        if (Objects.isNull(entityClass)) {
            final Class<?> clazz = ReflectionLookupFacade.getInstance().getGenericClazz(this.getClass(), 1);
            if (ReflectionLookupFacade.getInstance().checkGenericType(clazz, this.getClass(), 1)) {
                this.entityClass = (Class<E>) clazz;
            }
        }
        return entityClass;
    }

    /**
     * @param entityClass the entityClass to set
     */
    public final void setEntityClass(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * @return the keyClass
     */
    public final Class<K> getKeyClass() {
        if (Objects.isNull(keyClass)) {
            final Class<?> clazz = ReflectionLookupFacade.getInstance().getGenericClazz(this.getClass(), 0);
            if (ReflectionLookupFacade.getInstance().checkGenericType(clazz, this.getClass(), 1)) {
                this.keyClass = (Class<K>) clazz;
            }
        }
        return keyClass;
    }

    private SessionBean sessionBean;

    /**
     * @return the sessionBean
     */
    public final SessionBean getSessionBean() {
        return sessionBean;
    }

    /**
     * @param sessionBean the sessionBean to set
     */
    public final void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public final Boolean validateObject(Object entity) throws Exception {
        return validateEntity((E) entity);
    }

    public final Boolean validateFieldObject(Object entity, Object value) throws Exception {
        return validateField((E) entity, value);
    }

    public abstract Boolean validateEntity(E e) throws Exception;

    public abstract Boolean validateField(E e, Object fieldValue) throws Exception;


}
