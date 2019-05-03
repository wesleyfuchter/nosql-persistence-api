/**
 *
 */
package net.unibave.npa.core.persistence.impl;

import net.unibave.npa.core.persistence.abstracts.AbstractPersistenceContext;
import net.unibave.npa.core.persistence.abstracts.AbstractPersistenceProvider;
import net.unibave.npa.core.persistence.abstracts.IPersistenceContextFactory;
import net.unibave.npa.core.persistence.model.DeploymentDescriptorBean;
import net.unibave.npa.core.persistence.model.DeploymentDescriptorBeanFactory;
import net.unibave.npa.core.persistence.model.ProviderBean;
import net.unibave.npa.core.util.io.IOLookupFacade;
import net.unibave.npa.core.util.reflect.ReflectionLookupFacade;

import java.io.File;
import java.util.Map;

/**
 * @author wesley
 */
public class PersistenceContextFactory implements IPersistenceContextFactory {

    @Override
    public AbstractPersistenceContext factory(String contextKey, Map<String, Object> properties) throws Exception {
        return factory(contextKey, new DeploymentDescriptorBeanFactory(contextKey).factory(properties));
    }

    @Override
    public AbstractPersistenceContext factory(String contextKey, File propertiesFile) throws Exception {
        return factory(contextKey, IOLookupFacade.getInstance().getPropertiesReader().read(propertiesFile));
    }

    @Override
    public AbstractPersistenceContext factory(String contextKey, String filePath) throws Exception {
        return factory(contextKey, new File(filePath));
    }

    @Override
    public AbstractPersistenceContext factory(String contextKey, DeploymentDescriptorBean deploymentDescriptorBean) throws Exception {
        final ProviderBean providerBean = deploymentDescriptorBean.getProviderBean();
        final Class<? extends AbstractPersistenceProvider> providerClass = providerBean.getProviderClass();
        final Object providerObjectInstance = ReflectionLookupFacade.getInstance().createInstance(providerClass);
        if (providerObjectInstance instanceof AbstractPersistenceProvider) {
            final AbstractPersistenceProvider providerInstance = (AbstractPersistenceProvider) providerObjectInstance;
            final AbstractPersistenceContext context = providerInstance.getContextInstance();
            context.getContextBean().setDeploymentDescriptorBean(deploymentDescriptorBean);
            context.getContextBean().setAbstractPersistenceContext(context);
            return context;
        } else {
            return null;
        }
    }

    @Override
    public AbstractPersistenceContext factory(String contextKey) throws Exception {
        return factory(contextKey, IOLookupFacade.getInstance().getDeploymentDirectory());
    }

}
