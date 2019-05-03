package net.unibave.npa.core.persistence.impl;

import net.unibave.npa.core.persistence.abstracts.AbstractController;
import net.unibave.npa.core.persistence.abstracts.AbstractControllerAttribute;
import net.unibave.npa.core.persistence.abstracts.AbstractControllerEntity;
import net.unibave.npa.core.persistence.enumeration.ControllerEventsEnum;
import net.unibave.npa.core.persistence.metainf.Event;
import net.unibave.npa.core.persistence.model.AttributeBean;
import net.unibave.npa.core.persistence.model.ControllerBean;
import net.unibave.npa.core.persistence.model.EntityBean;
import net.unibave.npa.core.util.reflect.ReflectionLookupFacade;

import java.lang.reflect.Method;

public class FireControllerEventImpl {

    private volatile static FireControllerEventImpl instance;

    public static FireControllerEventImpl getInstance() {
        if (instance == null) {
            synchronized (FireControllerEventImpl.class) {
                if (instance == null) {
                    instance = new FireControllerEventImpl();
                }
            }
        }
        return instance;
    }

    private FireControllerEventImpl() {

    }

    public void fireEntityEvent(ControllerEventsEnum controllerEventsEnum, EntityBean entityBean) throws Exception {
        entityBean.getControllers().forEach((controller) -> {
            try {
                AbstractController abstractController = controller.getControllerInstance();
                if (abstractController instanceof AbstractControllerEntity) {
                    getEventMethod(controllerEventsEnum, controller).invoke((AbstractControllerEntity) abstractController, entityBean.getEntityInstance());
                } else {
                    throw new IllegalArgumentException("The entity controller must be instance of "+AbstractControllerEntity.class.getName());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private Method getEventMethod(ControllerEventsEnum controllerEventsEnum, ControllerBean controller) {
        return ReflectionLookupFacade.getInstance().getMethods(controller.getImplementClass().getSuperclass(), (method) -> {
                                return method.isAnnotationPresent(Event.class) && method.getAnnotation(Event.class).value().equals(controllerEventsEnum);
                        }).stream().findFirst().get();
    }

    public void fireAttributeEvent(ControllerEventsEnum controllerEventsEnum, EntityBean entityBean, AttributeBean attributeBean) throws Exception {
        attributeBean.getControllers().forEach((controller) -> {
            try {
                AbstractController abstractController = controller.getControllerInstance();
                if (abstractController instanceof AbstractControllerAttribute) {
                    getEventMethod(controllerEventsEnum, controller).invoke((AbstractControllerAttribute) abstractController, entityBean.getEntityInstance(), attributeBean.getValue());
                } else {
                    throw new IllegalArgumentException("The entity controller must be instance of "+AbstractControllerAttribute.class.getName());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

}
