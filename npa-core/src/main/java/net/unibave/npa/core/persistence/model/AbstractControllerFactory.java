package net.unibave.npa.core.persistence.model;

import net.unibave.npa.core.persistence.abstracts.AbstractController;
import net.unibave.npa.core.persistence.metainf.Controller;

import java.lang.annotation.Annotation;

/**
 * Created by wesley on 31/07/16.
 */
public abstract class AbstractControllerFactory<E> extends AbstractFactory<E,ControllerBean> {

    @Override
    protected Annotation getRepresentativeAnnotation() {
        return null;
    }

    @Override
    public final ControllerBean factory(E argument) throws Exception {
        setArgument(argument);
        final ControllerBean controllerBean = new ControllerBean();
        controllerBean.setAnnotationClass(getAnnotationClass());
        controllerBean.setSequence(getSequence());
        controllerBean.setControllerInstance(getControllerInstance());
        controllerBean.setImplementClass(getImplementClass());
        return controllerBean;
    }

    protected abstract Class<? extends Annotation> getAnnotationClass();

    protected abstract Integer getSequence();

    protected abstract AbstractController getControllerInstance() throws Exception;

    protected abstract Class<? extends AbstractController> getImplementClass();

}
