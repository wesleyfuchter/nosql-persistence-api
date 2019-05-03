package net.unibave.npa.core.persistence.model;

import net.unibave.npa.core.util.reflect.ReflectionLookupFacade;
import net.unibave.npa.core.util.reflect.abstracts.IInjector;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by wesley on 17/05/16.
 */
public class DeploymentDescriptorInstanceInjector implements IInjector {

    @Override
    public Object inject(Map<String,Object> inputValue, Object instance, Object key, Field field) throws Exception {
        final Map<String,Object> values = inputValue;
        if (values.containsKey(DeploymentDescriptorBean.DDK_DESCRIPTOR_CLASS)) {
            Class<?> descriptorClass = Class.forName(values.get(DeploymentDescriptorBean.DDK_DESCRIPTOR_CLASS).toString());
            Object descriptorInstance = ReflectionLookupFacade.getInstance().createInstance(descriptorClass);
            return descriptorInstance;
        }
        return null;
    }
}
