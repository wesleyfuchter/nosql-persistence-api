package net.unibave.npa.core.persistence.model;

import net.unibave.npa.core.util.reflect.abstracts.IInjector;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by wesley on 17/05/16.
 */
public class DeploymentDescriptorProviderBeanInjector implements IInjector {

    @Override
    public Object inject(Map<String,Object> inputValue, Object instance, Object key, Field field) throws Exception {
        final Map<String,Object> values = inputValue;
        final ProviderBeanFactory providerBeanFactory = new ProviderBeanFactory();
        final ProviderBean providerBean = providerBeanFactory.factory(values.get(key).toString());
        return providerBean;
    }
}
