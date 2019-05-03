package net.unibave.npa.core.persistence.impl.crud;

import net.unibave.npa.core.persistence.metainf.NextCrudClass;
import net.unibave.npa.core.persistence.model.EntityBean;
import net.unibave.npa.core.persistence.model.EntityKeyBean;
import net.unibave.npa.core.persistence.model.SessionBean;
import java.util.List;

@NextCrudClass(ProviderCRUD.class)
public final class SessionCRUD extends AbstractCRUD<EntityKeyBean,EntityBean> {

    public SessionCRUD(SessionBean sessionBean) {
        super(sessionBean);
    }

    @Override
    public EntityBean create(EntityBean entityBean) throws Exception {
        return super.<EntityKeyBean,EntityBean>getNextCrud().create(entityBean);
    }

    @Override
    public EntityBean read(EntityKeyBean entityKeyBean) throws Exception {
        return super.<EntityKeyBean,EntityBean>getNextCrud().read(entityKeyBean);
    }

    @Override
    public List<EntityBean> read() throws Exception {
        return super.<EntityKeyBean,EntityBean>getNextCrud().read();
    }

    @Override
    public EntityBean update(EntityBean entityBean) throws Exception {
        return super.<EntityKeyBean,EntityBean>getNextCrud().update(entityBean);
    }

    @Override
    public EntityBean delete(EntityBean entityBean) throws Exception {
        return super.<EntityKeyBean,EntityBean>getNextCrud().delete(entityBean);
    }
}
