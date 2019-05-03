package net.unibave.npa.core.persistence.impl.crud;

import net.unibave.npa.core.persistence.enumeration.ControllerEventsEnum;
import net.unibave.npa.core.persistence.exceptions.NoDataFoundException;
import net.unibave.npa.core.persistence.impl.FireControllerEventImpl;
import net.unibave.npa.core.persistence.metainf.NextCrudClass;
import net.unibave.npa.core.persistence.model.AttributeBean;
import net.unibave.npa.core.persistence.model.EntityBean;
import net.unibave.npa.core.persistence.model.EntityKeyBean;
import net.unibave.npa.core.persistence.model.SessionBean;
import net.unibave.npa.core.util.reflect.ReflectionLookupFacade;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NextCrudClass(ValidatorCRUD.class)
public final class InitialCRUD extends AbstractCRUD<Object,Object> {

    public InitialCRUD(SessionBean sessionBean) {
        super(sessionBean);
    }

    @Override
    public Object create(Object o) throws Exception {
        final EntityBean entityBean = getEntityBeanFactory().factory(o);
        FireControllerEventImpl.getInstance().fireEntityEvent(ControllerEventsEnum.BEFORE_CREATE,entityBean);
        final Object returnObject = super.<EntityKeyBean,EntityBean>getNextCrud().create(entityBean);
        FireControllerEventImpl.getInstance().fireEntityEvent(ControllerEventsEnum.AFTER_CREATE,entityBean);
        return returnObject;
    }

    @Override
    public Object read(Object o) throws Exception {
        final EntityKeyBean entityKeyBean = getSessionBean().getEntityBean().getEntityKey();
        entityKeyBean.setValue(o);
        EntityBean entityBean = super.<EntityKeyBean, EntityBean>getNextCrud().read(entityKeyBean);
        if (Objects.isNull(entityBean)) {
            throw new NoDataFoundException("The result is empty");
        }
        return entityBean.getEntityInstance();
    }

    @Override
    public List<Object> read() throws Exception {
        final List<Object> returnList = new ArrayList<>();
        final List<EntityBean> entityBeans = super.<EntityKeyBean,EntityBean>getNextCrud().read();
        entityBeans.forEach((row) -> {
            returnList.add(row.getEntityInstance());
        });
        if (Objects.isNull(entityBeans) || entityBeans.isEmpty()) {
            throw new NoDataFoundException("The result is empty");
        }
        return returnList;
    }

    @Override
    public Object update(Object o) throws Exception {
        final EntityBean entityBean = getEntityBeanFactory().factory(o);
        FireControllerEventImpl.getInstance().fireEntityEvent(ControllerEventsEnum.BEFORE_UPDATE,entityBean);
        final Object returnObject = super.<EntityKeyBean,EntityBean>getNextCrud().update(entityBean);
        FireControllerEventImpl.getInstance().fireEntityEvent(ControllerEventsEnum.AFTER_UPDATE,entityBean);
        return returnObject;
    }

    @Override
    public Object delete(Object o) throws Exception {
        final EntityBean entityBean = getEntityBeanFactory().factory(o);
        FireControllerEventImpl.getInstance().fireEntityEvent(ControllerEventsEnum.BEFORE_DELETE,entityBean);
        final Object returnObject = super.<EntityKeyBean,EntityBean>getNextCrud().delete(entityBean);
        FireControllerEventImpl.getInstance().fireEntityEvent(ControllerEventsEnum.AFTER_DELETE,entityBean);
        return returnObject;
    }
}
