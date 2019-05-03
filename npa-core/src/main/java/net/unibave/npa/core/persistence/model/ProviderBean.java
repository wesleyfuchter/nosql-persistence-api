package net.unibave.npa.core.persistence.model;

import net.unibave.npa.core.persistence.abstracts.AbstractPersistenceContext;
import net.unibave.npa.core.persistence.abstracts.AbstractPersistenceProvider;
import net.unibave.npa.core.persistence.abstracts.ICrud;
import net.unibave.npa.core.util.reflect.ReflectionLookupFacade;

import java.io.File;
import java.util.Map;
import java.util.Objects;

public class ProviderBean {

    private Class<? extends AbstractPersistenceProvider> providerClass;
    private String resourcePath;
    private Map<String, File> resources;
    private Map<String, Object> properties;
    private AbstractPersistenceProvider abstractPersistenceProvider;

    public AbstractPersistenceProvider getAbstractPersistenceProvider() throws Exception {
        return abstractPersistenceProvider;
    }

    public void setAbstractPersistenceProvider(AbstractPersistenceProvider abstractPersistenceProvider) {
        this.abstractPersistenceProvider = abstractPersistenceProvider;
    }

    /**
     * @return the providerClass
     */
    public Class<? extends AbstractPersistenceProvider> getProviderClass() {
        return providerClass;
    }

    /**
     * @param providerClass the providerClass to set
     */
    public void setProviderClass(Class<? extends AbstractPersistenceProvider> providerClass) {
        this.providerClass = providerClass;
    }

    /**
     * @return the resourcePath
     */
    public String getResourcePath() {
        return resourcePath;
    }

    /**
     * @param resourcePath the resourcePath to set
     */
    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    /**
     * @return the resources
     */
    public Map<String, File> getResources() {
        return resources;
    }

    /**
     * @param resources the resources to set
     */
    public void setResources(Map<String, File> resources) {
        this.resources = resources;
    }

    /**
     * @return the properties
     */
    public Map<String, Object> getProperties() {
        return properties;
    }

    /**
     * @param properties the properties to set
     */
    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public ProviderBean(Class<? extends AbstractPersistenceProvider> providerClass) {
        super();
        this.providerClass = providerClass;
    }

    public ProviderBean() {
        super();
    }

    public ICrud<EntityKeyBean,EntityBean> getProviderCrud(AbstractPersistenceContext abstractPersistenceContext, SessionBean sessionBean) throws Exception {
        return getAbstractPersistenceProvider().getCrudImplInstance(abstractPersistenceContext, sessionBean);
    }

}
