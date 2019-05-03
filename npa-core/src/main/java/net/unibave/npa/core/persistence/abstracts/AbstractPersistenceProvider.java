package net.unibave.npa.core.persistence.abstracts;

import net.unibave.npa.core.persistence.impl.crud.AbstractCRUD;
import net.unibave.npa.core.persistence.model.EntityBean;
import net.unibave.npa.core.persistence.model.EntityKeyBean;
import net.unibave.npa.core.persistence.model.SessionBean;
import net.unibave.npa.core.util.reflect.ReflectionLookupFacade;

public abstract class AbstractPersistenceProvider {

    public AbstractPersistenceProvider() {

    }

    public abstract Class<? extends AbstractPersistenceContext> getContextClass() throws Exception;

    public AbstractPersistenceContext getContextInstance() throws Exception {
        final AbstractPersistenceContext abstractPersistenceContext = (AbstractPersistenceContext)
                ReflectionLookupFacade.getInstance().createInstance(getContextClass());
        return abstractPersistenceContext;
    }

    @Deprecated
    public abstract Class<? extends AbstractProviderCrud> getCrudImplClass() throws Exception;

    @Deprecated
    public AbstractProviderCrud getCrudImplInstance(AbstractPersistenceContext abstractPersistenceContext, SessionBean sessionBean) throws Exception {
        final AbstractProviderCrud crud = (AbstractProviderCrud)
                ReflectionLookupFacade.getInstance().createInstance(getCrudImplClass(), abstractPersistenceContext, sessionBean);
        return crud;
    }

    public abstract Class<? extends AbstractCRUD<?,?>> getCRUD() throws Exception;

    public final <K,E> AbstractCRUD<K,E> getCRUDInstance(final SessionBean sessionBean) throws Exception {
        return ReflectionLookupFacade.getInstance().<AbstractCRUD<K,E>>createTypedInstance(getCRUD(), sessionBean);
    }

    public abstract Class<? extends IKeyGenerator> getKeyGenerator() throws Exception;

    public final IKeyGenerator getKeyGeneratorInstance() throws Exception {
        return ReflectionLookupFacade.getInstance().<IKeyGenerator>createTypedInstance(getKeyGenerator());
    }

}
