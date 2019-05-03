/**
 *
 */
package net.unibave.npa.core.persistence.model;

import net.unibave.npa.core.persistence.abstracts.AbstractPersistenceProvider;
import net.unibave.npa.core.common.abstracts.IFactory;
import net.unibave.npa.core.persistence.exceptions.IllegalPropertyValueException;
import net.unibave.npa.core.util.reflect.ReflectionLookupFacade;

/**
 * @author wesley
 */
public class ProviderBeanFactory implements IFactory<String, ProviderBean> {

    @Override
    public ProviderBean factory(String e) throws Exception {
        final String providerClassName = e;
        Class<?> forName = Class.forName(providerClassName);
        if (forName.getSuperclass().equals(AbstractPersistenceProvider.class)) {
            @SuppressWarnings("unchecked")
            final Class<? extends AbstractPersistenceProvider> providerClass = (Class<? extends AbstractPersistenceProvider>) forName;
            final ProviderBean providerBean = new ProviderBean(providerClass);
            final AbstractPersistenceProvider providerInstance =
                    (AbstractPersistenceProvider) ReflectionLookupFacade.getInstance().createInstance(providerClass);
            providerBean.setAbstractPersistenceProvider(providerInstance);
            return providerBean;
        } else {
            throw new IllegalPropertyValueException("The class must be extend " + AbstractPersistenceProvider.class.getName());
        }
    }

}
