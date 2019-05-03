package net.unibave.npa.core.persistence.impl.crud;

import net.unibave.npa.core.persistence.impl.validator.ValidatorImpl;
import net.unibave.npa.core.persistence.metainf.NextCrudClass;
import net.unibave.npa.core.persistence.model.EntityBean;
import net.unibave.npa.core.persistence.model.EntityKeyBean;
import net.unibave.npa.core.persistence.model.SessionBean;

import java.util.List;

/**
 * Created by wesley on 25/06/16.
 */
@NextCrudClass(SessionCRUD.class)
public final class ValidatorCRUD extends AbstractCRUD<EntityKeyBean,EntityBean> {

    public ValidatorCRUD(SessionBean sessionBean) {
        super(sessionBean);
    }

    @Override
    public EntityBean create(EntityBean entityBean) throws Exception {
        ValidatorImpl.validate(entityBean, getSessionBean());
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
        ValidatorImpl.validate(entityBean, getSessionBean());
        return super.<EntityKeyBean,EntityBean>getNextCrud().update(entityBean);
    }

    @Override
    public EntityBean delete(EntityBean entityBean) throws Exception {
        ValidatorImpl.validate(entityBean, getSessionBean());
        return super.<EntityKeyBean,EntityBean>getNextCrud().delete(entityBean);
    }
}
