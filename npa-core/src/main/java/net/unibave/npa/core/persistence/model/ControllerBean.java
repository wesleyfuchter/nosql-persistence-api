package net.unibave.npa.core.persistence.model;

import net.unibave.npa.core.persistence.abstracts.AbstractController;
import net.unibave.npa.core.persistence.metainf.Controller;

import java.io.Serializable;
import java.lang.annotation.Annotation;

/**
 * @author wesley
 */
public class ControllerBean implements Serializable {

    private static final long serialVersionUID = -8769077416618875416L;

    private Class<? extends Annotation> annotationClass;
    private Integer sequence;
    private AbstractController controllerInstance;
    private Class<? extends AbstractController> implementClass;

    protected ControllerBean() {
        super();
    }

    protected ControllerBean(Class<? extends Annotation> annotationClass, Integer sequence,
                             AbstractController controllerInstance, Class<? extends AbstractController> implementClass) {
        super();
        this.annotationClass = annotationClass;
        this.sequence = sequence;
        this.controllerInstance = controllerInstance;
        this.implementClass = implementClass;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((annotationClass == null) ? 0 : annotationClass.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ControllerBean)) {
            return false;
        }
        ControllerBean other = (ControllerBean) obj;
        if (annotationClass == null) {
            if (other.annotationClass != null) {
                return false;
            }
        } else if (!annotationClass.equals(other.annotationClass)) {
            return false;
        }
        return true;
    }

    public Class<? extends Annotation> getAnnotationClass() {
        return annotationClass;
    }

    protected void setAnnotationClass(Class<? extends Annotation> annotationClass) {
        this.annotationClass = annotationClass;
    }

    public Integer getSequence() {
        return sequence;
    }

    protected void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public AbstractController getControllerInstance() {
        return controllerInstance;
    }

    protected void setControllerInstance(AbstractController controllerInstance) {
        this.controllerInstance = controllerInstance;
    }

    public Class<? extends AbstractController> getImplementClass() {
        return implementClass;
    }

    protected void setImplementClass(Class<? extends AbstractController> implementClass) {
        this.implementClass = implementClass;
    }

    public static Boolean isController(Annotation annotation) {
        return annotation.annotationType().isAnnotationPresent(Controller.class);
    }

}
