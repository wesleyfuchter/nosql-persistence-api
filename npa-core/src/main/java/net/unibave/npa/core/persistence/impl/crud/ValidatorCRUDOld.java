/**
 *
 */
package net.unibave.npa.core.persistence.impl.crud;

import net.unibave.npa.core.persistence.abstracts.ICrud;
import net.unibave.npa.core.persistence.impl.validator.ValidatorImpl;
import net.unibave.npa.core.persistence.model.*;

import java.util.List;
import java.util.Objects;

/**
 * @author wesley
 */
@Deprecated
public class ValidatorCRUDOld implements ICrud<EntityKeyBean, EntityBean> {

    private final SessionBean session;
    private ICrud<EntityKeyBean, EntityBean> crud;

    /**
     * @return the crud
     */
    public ICrud<EntityKeyBean, EntityBean> getCrud() throws Exception {
        if (Objects.isNull(crud)) {
            crud = new ProviderCRUDOld(session);
        }
        return crud;
    }

    /**
     * @param crud the crud to set
     */
    public void setCrud(ICrud<EntityKeyBean, EntityBean> crud) {
        this.crud = crud;
    }

    public ValidatorCRUDOld(SessionBean session) {
        super();
        this.session = session;
    }

    @Override
    public EntityBean create(EntityBean e) throws Exception {
        validate(e);
        return getCrud().create(e);
    }

    @Override
    public EntityBean read(EntityKeyBean k) throws Exception {
        return getCrud().read(k);
    }

    @Override
    public List<EntityBean> read() throws Exception {
        return getCrud().read();
    }

    @Override
    public EntityBean update(EntityBean e) throws Exception {
        validate(e);
        return getCrud().update(e);
    }

    @Override
    public EntityBean delete(EntityBean e) throws Exception {
        validate(e);
        return getCrud().delete(e);
    }

    private void validate(EntityBean e) throws Exception {
        ValidatorImpl.validate(e,session);
    }
}
