package net.unibave.npa.core.persistence.model;

import net.unibave.npa.core.common.abstracts.IFactory;
import net.unibave.npa.core.util.io.IOLookupFacade;
import net.unibave.npa.core.util.reflect.ReflectionLookupFacade;

import java.io.File;
import java.util.Map;

/**
 * Created by wesley on 16/05/16.
 */
public class ConnectionBeanFactory implements IFactory<File, ConnectionBean> {

    @Override
    public ConnectionBean factory(final File file) throws Exception {
        final Map<String, Object> properties = IOLookupFacade.getInstance().getPropertiesReader().read(file);
        final ConnectionBean connectionBean = (ConnectionBean)
                ReflectionLookupFacade.getInstance().propertiesToObject(properties,ConnectionBean.class);
        return connectionBean;
    }

}
