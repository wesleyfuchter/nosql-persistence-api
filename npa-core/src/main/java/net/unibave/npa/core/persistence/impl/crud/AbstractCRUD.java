package net.unibave.npa.core.persistence.impl.crud;

import net.unibave.npa.core.persistence.abstracts.ICrud;
import net.unibave.npa.core.persistence.metainf.NextCrudClass;
import net.unibave.npa.core.persistence.model.*;

/**
 * Created by wesley on 25/06/16.
 */
public abstract class AbstractCRUD<K,E> implements ICrud<K,E> {

    private final ObjectEntityBeanFactory entityBeanFactory;
    private final EntityKeyFactoryImpl entityKeyBeanFactory;
    private final Class<?> entityClass;
    private final Class<?> keyClass;
    private final SessionBean sessionBean;

    protected AbstractCRUD(final SessionBean sessionBean) {
        this.entityBeanFactory = new ObjectEntityBeanFactory();
        this.entityKeyBeanFactory = new EntityKeyFactoryImpl();
        this.entityClass = sessionBean.getEntityClass();
        this.keyClass = sessionBean.getKeyClass();
        this.sessionBean = sessionBean;
    }

    protected final ObjectEntityBeanFactory getEntityBeanFactory() {
        return entityBeanFactory;
    }

    @SuppressWarnings("unchecked")
    protected <TK,TE> AbstractCRUD<TK,TE> getNextCrud() throws Exception {
        if(this.getClass().isAnnotationPresent(NextCrudClass.class)) {
            final CrudFactory crudFactory = new CrudFactory();
            return (AbstractCRUD<TK, TE>) crudFactory.factory(this.getClass().getAnnotation(NextCrudClass.class).value(), sessionBean);
        } else {
            throw new IllegalArgumentException("The class "+this.getClass().getName()+" must be noted by "+NextCrudClass.class.getName());
        }
    }

    protected EntityKeyFactoryImpl getEntityKeyBeanFactory() {
        return entityKeyBeanFactory;
    }

    protected Class<?> getEntityClass() {
        return entityClass;
    }

    protected Class<?> getKeyClass() {
        return keyClass;
    }

    protected SessionBean getSessionBean() {
        return sessionBean;
    }

}

