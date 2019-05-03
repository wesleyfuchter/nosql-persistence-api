package net.unibave.npa.core.persistence.model;

import net.unibave.npa.core.persistence.exceptions.IllegalPropertyValueException;
import net.unibave.npa.core.util.io.IOLookupFacade;
import net.unibave.npa.core.util.reflect.ReflectionLookupFacade;
import net.unibave.npa.core.util.reflect.abstracts.IInjector;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wesley on 17/05/16.
 */
public class DeploymentDescriptorConnectionListInjector implements IInjector {

    @Override
    public Object inject(Map<String,Object> inputValue, Object instance, Object key, Field field) throws Exception {
        final List<ConnectionBean> returnList = new ArrayList<>();
        final Map<String,Object> values = inputValue;
        final String connectionsPath = values.get(key).toString();
        final List<File> connectionFiles = IOLookupFacade.getInstance().getPropertiesFiles(connectionsPath);
        final ConnectionBeanFactory connectionBeanFactory = new ConnectionBeanFactory();
        connectionFiles.forEach((row) -> {
            try {
                final ConnectionBean connectionBean = connectionBeanFactory.factory(row);
                if (!returnList.contains(connectionBean)) {
                    returnList.add(connectionBean);
                } else {
                    throw new IllegalPropertyValueException("Id: "+connectionBean.getId()+" must be unique!");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return returnList;
    }
}
