package net.unibave.npa.core.persistence.abstracts;

import net.unibave.npa.core.persistence.model.ContextBean;
import net.unibave.npa.core.persistence.model.SessionBean;

import java.util.Objects;

public abstract class AbstractPersistenceContext {

    public AbstractPersistenceContext() {
    }

    private ContextBean contextBean;

    /**
     * @return the contextBean
     */
    public final ContextBean getContextBean() {
        if (Objects.isNull(contextBean)) {
            contextBean = new ContextBean();
        }
        return contextBean;
    }

    public SessionBean createSession(Class<?> entityClass) throws Exception {
        return getContextBean().createSession(entityClass);
    }

    protected abstract void clearContext() throws Exception;

}
