package net.unibave.npa.core.persistence.abstracts;

import net.unibave.npa.core.persistence.model.EntityBean;
import net.unibave.npa.core.persistence.model.EntityKeyBean;
import net.unibave.npa.core.persistence.model.SessionBean;

/**
 * Created by wesley on 18/05/16.
 */
public abstract class AbstractProviderCrud implements ICrud<EntityKeyBean, EntityBean> {

    private AbstractPersistenceContext context;
    private SessionBean sessionBean;

    public AbstractProviderCrud(AbstractPersistenceContext context, SessionBean sessionBean) {
        this.sessionBean = sessionBean;
        this.context = context;
    }

}
