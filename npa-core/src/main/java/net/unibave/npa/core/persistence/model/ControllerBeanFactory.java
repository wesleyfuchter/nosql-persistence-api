/**
 *
 */
package net.unibave.npa.core.persistence.model;

import net.unibave.npa.core.common.abstracts.IFactory;
import net.unibave.npa.core.persistence.metainf.Controller;

/**
 * @author wesley
 */
@Deprecated
public class ControllerBeanFactory implements IFactory<Controller, ControllerBean> {

    @Override
    public ControllerBean factory(final Controller e) throws Exception {
        final ControllerBean controllerBean = new ControllerBean();
        controllerBean.setSequence(e.sequence());
        controllerBean.setAnnotationClass(e.annotationType());
        controllerBean.setImplementClass(e.implementClass());
        controllerBean.setControllerInstance(e.implementClass().newInstance());
        return controllerBean;
    }

}
