/**
 *
 */
package net.unibave.npa.core.persistence.model;

import net.unibave.npa.core.common.abstracts.IFactory;
import net.unibave.npa.core.util.reflect.ReflectionLookupFacade;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wesley
 */
public class DeploymentDescriptorBeanFactory implements IFactory<Map<String, Object>, DeploymentDescriptorBean> {

    private final String contextKey;

    public DeploymentDescriptorBeanFactory(String contextKey) {
        super();
        this.contextKey = contextKey;
    }

    @Override
    public DeploymentDescriptorBean factory(final Map<String, Object> e) throws Exception {
        final Map<String, Object> unitProperties = new HashMap<>();
        e.keySet().stream().filter((row) -> row.startsWith(contextKey.concat("."))).forEach((row) -> {
            final String key = row.substring(contextKey.length() + 1, row.length());
            unitProperties.put(key, e.get(row));
        });
        if (unitProperties.containsKey(DeploymentDescriptorBean.DDK_DESCRIPTOR_CLASS)) {
            Class<?> descriptorClass = Class.forName(unitProperties.get(DeploymentDescriptorBean.DDK_DESCRIPTOR_CLASS).toString());
            Object descriptorInstance = ReflectionLookupFacade.getInstance().createInstance(descriptorClass);
            unitProperties.clear();
            unitProperties.putAll(ReflectionLookupFacade.getInstance().objectToProperties(descriptorInstance));
        }
        final Object deploymentDescriptorObject =
                ReflectionLookupFacade.getInstance().propertiesToObject(unitProperties, DeploymentDescriptorBean.class);
        final DeploymentDescriptorBean deploymentDescriptorBean = (DeploymentDescriptorBean) deploymentDescriptorObject;
        return deploymentDescriptorBean;
    }

}
