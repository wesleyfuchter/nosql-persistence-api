package net.unibave.npa.core.persistence.model;

import net.unibave.npa.core.persistence.exceptions.IllegalPropertyValueException;
import net.unibave.npa.core.util.reflect.abstracts.IInjector;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by wesley on 17/05/16.
 */
public class DeploymentDescriptorDefaultConnectionInjector implements IInjector {

    @Override
    public Object inject(Map<String,Object> inputValue, Object instance, Object key, Field field) throws Exception {
        if (instance instanceof  DeploymentDescriptorBean) {
            final Map<String,Object> values = inputValue;
            final String defaultConnectionId = values.get(key).toString();
            final DeploymentDescriptorBean deploymentDescriptorBean = (DeploymentDescriptorBean) instance;
            final Predicate<ConnectionBean> filter = (row) -> row.getId().equals(defaultConnectionId);
            final List<ConnectionBean> connections = deploymentDescriptorBean.getConnections().stream().filter(filter).collect(Collectors.toList());
            if (connections.isEmpty()) {
                throw new IllegalPropertyValueException("The connection id: " + defaultConnectionId + " not exists!");
            }
            final ConnectionBean defaultConnection = connections.get(0);
            return defaultConnection;
        } else {
            return null;
        }
    }
}
