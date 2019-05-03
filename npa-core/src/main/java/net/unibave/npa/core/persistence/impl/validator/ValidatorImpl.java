package net.unibave.npa.core.persistence.impl.validator;

import net.unibave.npa.core.persistence.model.AttributeBean;
import net.unibave.npa.core.persistence.model.EntityBean;
import net.unibave.npa.core.persistence.model.SessionBean;
import net.unibave.npa.core.persistence.model.ValidatorBean;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by wesley on 26/06/16.
 */
public final class ValidatorImpl {

    //TODO improve to single responsibility
    public static void validate(final  EntityBean entityBean, final SessionBean sessionBean) throws Exception {
        List<ValidatorBean> validators = entityBean.getValidators();
        Comparator<ValidatorBean> comparable = (validatorA, validatorB) -> {
            if (validatorA.getSequence() > validatorB.getSequence()) {
                return 1;
            } else if (validatorA.getSequence() < validatorB.getSequence()) {
                return -1;
            } else {
                return 0;
            }
        };
        Collections.sort(validators, comparable);
        for (ValidatorBean validatorBean : validators) {
            validatorBean.validateEntity(entityBean, sessionBean);
        }
        validateAttributes(entityBean, sessionBean, comparable);
    }

    private static void validateAttributes(EntityBean entityBean, SessionBean sessionBean, Comparator<ValidatorBean> comparable) throws Exception {
        List<ValidatorBean> validators;
        for (AttributeBean attributeBean : entityBean.getAttributes()) {
            validators = attributeBean.getValidators();
            Collections.sort(validators, comparable);
            for (ValidatorBean validatorBean : validators) {
                validatorBean.validateField(entityBean, attributeBean, sessionBean);
            }
        }
    }

}
