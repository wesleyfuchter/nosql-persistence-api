package net.unibave.npa.core.persistence.impl.crud;

import net.unibave.npa.core.persistence.abstracts.AbstractPersistenceContext;
import net.unibave.npa.core.persistence.abstracts.ICrud;
import net.unibave.npa.core.persistence.model.SessionBean;
import net.unibave.npa.core.util.reflect.ReflectionLookupFacade;
import java.util.List;
import java.util.Objects;

/**
 * Created by wesley on 26/06/16.
 */
@SuppressWarnings("unchecked")
public class GenericCRUD<K,E> implements ICrud<K,E> {

    private final AbstractPersistenceContext context;
    private final Class<E> entityClass;

    public GenericCRUD(AbstractPersistenceContext context, Class<E> entityClass) {
        Objects.<AbstractPersistenceContext>requireNonNull(context, "The context can't be null");
        Objects.<Class>requireNonNull(entityClass, "The class can't be null");
        this.entityClass = entityClass;
        this.context = context;
    }

    @Override
    public E create(E e) throws Exception {
        SessionBean sessionBean = context.createSession(entityClass);
        sessionBean.open();
        e = (E) sessionBean.create(e);
        sessionBean.close();
        return e;
    }

    @Override
    public E read(K k) throws Exception {
        SessionBean sessionBean = context.createSession(entityClass);
        sessionBean.open();
        E e = (E) sessionBean.read(k);
        sessionBean.close();
        return e;
    }

    @Override
    public List<E> read() throws Exception {
        SessionBean sessionBean = context.createSession(entityClass);
        sessionBean.open();
        List<E> e = (List<E>) sessionBean.read();
        sessionBean.close();
        return e;
    }

    @Override
    public E update(E e) throws Exception {
        SessionBean sessionBean = context.createSession(entityClass);
        sessionBean.open();
        e = (E) sessionBean.update(e);
        sessionBean.close();
        return e;
    }

    @Override
    public E delete(E e) throws Exception {
        SessionBean sessionBean = context.createSession(entityClass);
        sessionBean.open();
        e = (E) sessionBean.delete(e);
        sessionBean.close();
        return e;
    }
}
