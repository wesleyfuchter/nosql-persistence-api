/**
 *
 */
package net.unibave.npa.core.persistence.abstracts;

import net.unibave.npa.core.persistence.impl.crud.GenericCRUD;
import net.unibave.npa.core.persistence.metainf.ContextFactory;
import net.unibave.npa.core.persistence.model.SessionBean;
import net.unibave.npa.core.util.reflect.ReflectionLookupFacade;

import java.util.List;
import java.util.Objects;

/**
 * @author wesley
 */
public abstract class AbstractDAO<K, E> implements ICrud<K, E> {

    private final GenericCRUD<K,E> genericCRUD;
    private AbstractPersistenceContext context;
    private IContextFactory contextFactory;
    private final Class<E> entityClass;

    public AbstractDAO() {
        this.entityClass = ReflectionLookupFacade.getInstance().<E>getGenericClass(this.getClass(), 1);
        try {
            this.genericCRUD = new GenericCRUD<K, E>(getContext(),this.entityClass);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private IContextFactory getContextFactory() throws Exception {
        if (Objects.isNull(contextFactory)) {
            if (entityClass.isAnnotationPresent(ContextFactory.class)) {
                final ContextFactory contextFactoryAnnotation = entityClass.getAnnotation(ContextFactory.class);
                this.contextFactory = ReflectionLookupFacade.getInstance().<IContextFactory>createTypedInstance(contextFactoryAnnotation.value());
            }
        }
        return contextFactory;
    }

    protected final AbstractPersistenceContext getContext() throws Exception {
        if (Objects.isNull(context)) {
            context = getContextFactory().factory();
        }
        return context;
    }

    @Override
    public E create(E e) throws Exception {
        return genericCRUD.create(e);
    }

    @Override
    public E read(K k) throws Exception {
        return genericCRUD.read(k);
    }

    @Override
    public List<E> read() throws Exception {
        return genericCRUD.read();
    }

    @Override
    public E update(E e) throws Exception {
        return genericCRUD.update(e);
    }

    @Override
    public E delete(E e) throws Exception {
        return genericCRUD.delete(e);
    }
}
