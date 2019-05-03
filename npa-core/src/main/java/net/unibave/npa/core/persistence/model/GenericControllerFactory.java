package net.unibave.npa.core.persistence.model;

import net.unibave.npa.core.persistence.abstracts.AbstractController;
import net.unibave.npa.core.persistence.metainf.Controller;
import net.unibave.npa.core.util.reflect.ReflectionLookupFacade;

import java.lang.annotation.Annotation;

/**
 * Created by wesley on 31/07/16.
 */
public final class GenericControllerFactory extends AbstractControllerFactory<Controller> {

    @Override
    protected Controller getRepresentativeAnnotation() {
        return getArgument();
    }

    @Override
    protected Class<? extends Annotation> getAnnotationClass() {
        return getArgument().annotationType();
    }

    @Override
    protected Integer getSequence() {
        return getArgument().sequence();
    }

    @Override
    protected AbstractController getControllerInstance() throws Exception {
        return ReflectionLookupFacade.getInstance().<AbstractController>createTypedInstance(getArgument().implementClass());
    }

    @Override
    protected Class<? extends AbstractController> getImplementClass() {
        return getArgument().implementClass();
    }
}
