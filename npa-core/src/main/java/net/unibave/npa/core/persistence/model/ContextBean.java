package net.unibave.npa.core.persistence.model;

import net.unibave.npa.core.persistence.abstracts.AbstractPersistenceContext;
import net.unibave.npa.core.persistence.abstracts.ICrud;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ContextBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private List<SessionBean> sessions;
    private List<ConnectionBean> connections;
    private ContextStorageBean contextStorage;
    private List<CacheBean> cache;
    private DeploymentDescriptorBean deploymentDescriptorBean;
    private AbstractPersistenceContext abstractPersistenceContext;

    public SessionBean createSession(Class<?> entityClass) throws Exception {
        final SessionBean sessionBean = new SessionBean();
        sessionBean.setEntityBean(new EntityBeanFactory().factory(entityClass.newInstance()));
        sessionBean.setEntityClass(entityClass);
        sessionBean.setContextBean(this);
        sessionBean.setConnection(getDeploymentDescriptorBean().getDefaultConnection());
        getSessions().add(sessionBean);
        return sessionBean;
    }

    /**
     * @return the sessions
     */
    public List<SessionBean> getSessions() {
        if (Objects.isNull(sessions)) {
            sessions = new ArrayList<>();
        }
        return sessions;
    }

    /**
     * @param sessions the sessions to set
     */
    public void setSessions(List<SessionBean> sessions) {
        this.sessions = sessions;
    }

    /**
     * @return the connections
     */
    public List<ConnectionBean> getConnections() {
        return connections;
    }

    /**
     * @param connections the connections to set
     */
    public void setConnections(List<ConnectionBean> connections) {
        this.connections = connections;
    }

    /**
     * @return the contextStorage
     */
    public ContextStorageBean getContextStorage() {
        return contextStorage;
    }

    /**
     * @param contextStorage the contextStorage to set
     */
    public void setContextStorage(ContextStorageBean contextStorage) {
        this.contextStorage = contextStorage;
    }

    /**
     * @return the cache
     */
    public List<CacheBean> getCache() {
        return cache;
    }

    /**
     * @param cache the cache to set
     */
    public void setCache(List<CacheBean> cache) {
        this.cache = cache;
    }

    /**
     * @return the deploymentDescriptorBean
     */
    public DeploymentDescriptorBean getDeploymentDescriptorBean() {
        return deploymentDescriptorBean;
    }

    /**
     * @param deploymentDescriptorBean the deploymentDescriptorBean to set
     */
    public void setDeploymentDescriptorBean(DeploymentDescriptorBean deploymentDescriptorBean) {
        this.deploymentDescriptorBean = deploymentDescriptorBean;
    }

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public AbstractPersistenceContext getAbstractPersistenceContext() {
        return abstractPersistenceContext;
    }

    public void setAbstractPersistenceContext(AbstractPersistenceContext abstractPersistenceContext) {
        this.abstractPersistenceContext = abstractPersistenceContext;
    }
}
