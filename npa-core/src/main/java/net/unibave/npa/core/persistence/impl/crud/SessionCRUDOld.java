/**
 *
 */
package net.unibave.npa.core.persistence.impl.crud;

import net.unibave.npa.core.persistence.abstracts.ICrud;
import net.unibave.npa.core.persistence.enumeration.EntityBeanStatus;
import net.unibave.npa.core.persistence.model.EntityBean;
import net.unibave.npa.core.persistence.model.EntityBeanFactory;
import net.unibave.npa.core.persistence.model.EntityKeyBean;
import net.unibave.npa.core.persistence.model.SessionBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author wesley
 */
@Deprecated
public class SessionCRUDOld implements ICrud<Object, Object> {

    private ICrud<EntityKeyBean, EntityBean> crud;
    private final SessionBean session;
    private EntityBeanFactory entityBeanFactory;

    public final EntityBeanFactory getEntityBeanFactory() {
        if (Objects.isNull(entityBeanFactory)) {
            entityBeanFactory = new EntityBeanFactory();
        }
        return entityBeanFactory;
    }

    public ICrud<EntityKeyBean, EntityBean> getCrud() {
        if (Objects.isNull(crud)) {
            this.crud = new ValidatorCRUDOld(session);
        }
        return crud;
    }

    public SessionBean getSession() {
        return session;
    }

    public SessionCRUDOld(SessionBean session) {
        super();
        this.session = session;
    }

    /**
     * @param e
     * @throws Exception
     * @see EntityBean
     */
    public Object create(Object e) throws Exception {
        EntityBean entityBean = getEntityBeanFactory().factory(e);
        final EntityBean returnBean = getCrud().create(entityBean);
        entityBean.setStatus(EntityBeanStatus.QUERY);
        return returnBean;
    }

    /**
     * @param k
     * @return
     * @throws Exception
     * @see EntityKeyBean
     */
    public Object read(Object k) throws Exception {
        final EntityKeyBean entityKeyBean = session.getEntityBean().getEntityKey();
        entityKeyBean.getAttribute().setValue(k);
        final EntityBean returnEntity = getCrud().read(entityKeyBean);
        returnEntity.setStatus(EntityBeanStatus.QUERY);
        return returnEntity.getEntityInstance();
    }

    /**
     * @return
     * @throws Exception
     * @see ValidatorCRUDOld
     */
    public List<Object> read() throws Exception {
        final List<Object> returnList = new ArrayList<>();
        final List<EntityBean> entityBeans = getCrud().read();
        entityBeans.forEach((row) -> {
            row.setStatus(EntityBeanStatus.QUERY);
            returnList.add(row.getEntityInstance());
        });
        return returnList;
    }

    /**
     * @param e
     * @throws Exception
     * @see EntityBean
     */
    public Object update(Object e) throws Exception {
        final EntityBean entityBean = getEntityBeanFactory().factory(e);
        final EntityBean returnEntity = getCrud().update(entityBean);
        entityBean.setStatus(EntityBeanStatus.QUERY);
        return returnEntity;
    }

    /**
     * @param e
     * @throws Exception
     * @see EntityBean
     */
    public Object delete(Object e) throws Exception {
        final EntityBean entityBean = getEntityBeanFactory().factory(e);
        final EntityBean returnEntity = getCrud().delete(entityBean);
        entityBean.setStatus(EntityBeanStatus.REMOVED);
        return returnEntity;
    }


}
