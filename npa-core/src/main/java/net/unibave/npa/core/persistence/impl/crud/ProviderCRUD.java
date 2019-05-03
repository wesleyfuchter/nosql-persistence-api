package net.unibave.npa.core.persistence.impl.crud;

import net.unibave.npa.core.persistence.impl.DefaultKeyGenerator;
import net.unibave.npa.core.persistence.model.EntityBean;
import net.unibave.npa.core.persistence.model.EntityKeyBean;
import net.unibave.npa.core.persistence.model.SessionBean;
import net.unibave.npa.core.util.reflect.ReflectionLookupFacade;
import java.util.List;
import java.util.Objects;

public final class ProviderCRUD extends AbstractCRUD<EntityKeyBean, EntityBean> {

    @Override
    protected <TK, TE> AbstractCRUD<TK, TE> getNextCrud() throws Exception {
        final AbstractCRUD<TK, TE> crudInstance = getSessionBean().getAbstractPersistenceProvider().<TK, TE>getCRUDInstance(getSessionBean());
        return crudInstance;
    }

    public ProviderCRUD(SessionBean sessionBean) {
        super(sessionBean);
    }


    private void generateKey(EntityBean entityBean) throws Exception {
        final DefaultKeyGenerator defaultKeyGenerator = new DefaultKeyGenerator();
        final Object value = defaultKeyGenerator.generate(entityBean.getEntityInstance(), getSessionBean());
        ReflectionLookupFacade.getInstance().setFieldValue(entityBean.getEntityKey().getAttribute().getField(), value, entityBean.getEntityInstance());
    }

    @Override
    public EntityBean create(EntityBean entityBean) throws Exception {
        if (Objects.isNull(entityBean.getKeyValue())) {
            generateKey(entityBean);
        }
        return this.<EntityKeyBean, EntityBean>getNextCrud().create(entityBean);
    }

    @Override
    public EntityBean read(EntityKeyBean entityKeyBean) throws Exception {
        return this.<EntityKeyBean, EntityBean>getNextCrud().read(entityKeyBean);
    }

    @Override
    public List<EntityBean> read() throws Exception {
        return this.<EntityKeyBean, EntityBean>getNextCrud().read();
    }

    @Override
    public EntityBean update(EntityBean entityBean) throws Exception {
        return this.<EntityKeyBean, EntityBean>getNextCrud().update(entityBean);
    }

    @Override
    public EntityBean delete(EntityBean entityBean) throws Exception {
        return this.<EntityKeyBean, EntityBean>getNextCrud().delete(entityBean);
    }

}
