package net.unibave.npa.core.persistence.abstracts;

import net.unibave.npa.core.persistence.enumeration.ControllerEventsEnum;
import net.unibave.npa.core.persistence.metainf.Event;

public abstract class AbstractControllerEntity<E> extends AbstractController<E,Object> {

    public AbstractControllerEntity() {

    }

    @Event(ControllerEventsEnum.BEFORE_CREATE)
    public E beforeCreate(E entity) {
        return entity;
    }

    @Event(ControllerEventsEnum.AFTER_CREATE)
    public E afterCreate(E entity) {
        return entity;
    }

    @Event(ControllerEventsEnum.BEFORE_UPDATE)
    public E beforeUpdate(E entity) {
        return entity;
    }

    @Event(ControllerEventsEnum.AFTER_UPDATE)
    public E afterUpdate(E entity) {
        return entity;
    }

    @Event(ControllerEventsEnum.BEFORE_DELETE)
    public E beforeDelete(E entity) {
        return entity;
    }

    @Event(ControllerEventsEnum.AFTER_DELETE)
    public E afterDelete(E entity) {
        return entity;
    }

}
