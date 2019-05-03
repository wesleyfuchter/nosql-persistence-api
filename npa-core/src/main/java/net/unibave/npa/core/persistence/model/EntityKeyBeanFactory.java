/**
 *
 */
package net.unibave.npa.core.persistence.model;

import net.unibave.npa.core.common.abstracts.IFactory;
import net.unibave.npa.core.persistence.metainf.EntityKey;

/**
 * @author wesley
 */
@Deprecated
public class EntityKeyBeanFactory implements IFactory<AttributeBean, EntityKeyBean> {

    @Override
    public EntityKeyBean factory(final AttributeBean e) throws Exception {
        final EntityKey entityKeyAnnotation = e.getField().getAnnotation(EntityKey.class);
        final EntityKeyBean entityKeyBean = new EntityKeyBean();
        entityKeyBean.setAttribute(e);
        entityKeyBean.setEntityBean(e.getEntityBean());
        entityKeyBean.setKeyGenerator(entityKeyAnnotation.iKeyGenerator().newInstance());
        entityKeyBean.setKeyType(entityKeyAnnotation.keyType());
        return entityKeyBean;
    }

}
