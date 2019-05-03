/**
 *
 */
package net.unibave.npa.core.persistence.model;

import net.unibave.npa.core.common.abstracts.IFactory;
import net.unibave.npa.core.persistence.metainf.Validator;

/**
 * @author wesley
 */
@Deprecated
public class ValidatorBeanFactory implements IFactory<Validator, ValidatorBean> {

    @Override
    public ValidatorBean factory(final Validator e) throws Exception {
        final ValidatorBean validatorBean = new ValidatorBean();
        validatorBean.setSequence(e.sequence());
        validatorBean.setAnnotationClass(e.annotationType());
        validatorBean.setImplementClass(e.implementClass());
        validatorBean.setValidatorInstance(e.implementClass().newInstance());
        validatorBean.setExceptionToThrow(e.exceptionToThrowClass());
        validatorBean.setMessage(e.message());
        return validatorBean;
    }

}
