package net.unibave.npa.core.persistence.abstracts;

import net.unibave.npa.core.persistence.enumeration.ControllerEventsEnum;
import net.unibave.npa.core.persistence.metainf.Event;

public abstract class AbstractControllerAttribute<E, A> extends AbstractController<E,A> {

    public AbstractControllerAttribute() {

    }

    @Event(ControllerEventsEnum.BEFORE_CREATE)
    public A beforeCreate(E entity, A attribute) {
        return attribute;
    }

    @Event(ControllerEventsEnum.AFTER_CREATE)
    public A afterCreate(E entity, A attribute) {
        return attribute;
    }

    @Event(ControllerEventsEnum.BEFORE_UPDATE)
    public A beforeUpdate(E entity, A attribute) {
        return attribute;
    }

    @Event(ControllerEventsEnum.AFTER_UPDATE)
    public A afterUpdate(E entity, A attribute) {
        return attribute;
    }

    @Event(ControllerEventsEnum.BEFORE_DELETE)
    public A beforeDelete(E entity, A attribute) {
        return attribute;
    }

    @Event(ControllerEventsEnum.AFTER_DELETE)
    public A afterDelete(E entity, A attribute) {
        return attribute;
    }

}
