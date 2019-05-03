package net.unibave.npa.core.persistence.impl.crud;

import net.unibave.npa.core.persistence.abstracts.ICrud;
import net.unibave.npa.core.persistence.model.EntityBean;
import net.unibave.npa.core.persistence.model.EntityKeyBean;
import net.unibave.npa.core.persistence.model.SessionBean;
import java.util.List;
import java.util.Objects;

@Deprecated
public class ProviderCRUDOld implements ICrud<EntityKeyBean, EntityBean> {

    private final SessionBean session;
    private ICrud<EntityKeyBean,EntityBean> providerCrudImpl;

    public ICrud<EntityKeyBean, EntityBean> getProviderCrudImpl() throws Exception {
        if (Objects.isNull(providerCrudImpl)) {
            providerCrudImpl =
                    session.getContextBean().
                            getDeploymentDescriptorBean().
                            getProviderBean().
                            getProviderCrud(session.getContextBean().
                                                    getAbstractPersistenceContext(), session);
        }
        return providerCrudImpl;
    }

    public ProviderCRUDOld(SessionBean session) throws Exception {
        super();
        this.session = session;
    }

    @Override
    public EntityBean create(EntityBean e) throws Exception {
        return getProviderCrudImpl().create(e);
    }

    @Override
    public EntityBean read(EntityKeyBean k) throws Exception {
        return getProviderCrudImpl().read(k);
    }

    @Override
    public List<EntityBean> read() throws Exception {
        return getProviderCrudImpl().read();
    }

    @Override
    public EntityBean update(EntityBean e) throws Exception {
        return getProviderCrudImpl().update(e);
    }

    @Override
    public EntityBean delete(EntityBean e) throws Exception {
        return getProviderCrudImpl().delete(e);
    }


}
