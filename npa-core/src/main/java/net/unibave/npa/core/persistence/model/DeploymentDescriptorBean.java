package net.unibave.npa.core.persistence.model;

import net.unibave.npa.core.util.reflect.metainf.Property;

import java.util.List;

public class DeploymentDescriptorBean {

    public static final String DDK_PROVIDER_CLASS = "providerClass";
    public static final String DDK_CONNECTIONS_PATH = "connectionsPath";
    public static final String DDK_DEFAULT_CONNECTION_ID = "defaultConnectionId";
    public static final String DDK_DESCRIPTOR_CLASS = "descriptorClass";

    @Property(key = DDK_PROVIDER_CLASS, injector = DeploymentDescriptorProviderBeanInjector.class, nullable = false)
    private ProviderBean providerBean;

    @Property(key = DDK_CONNECTIONS_PATH, nullable = false)
    private String connectionsPath;

    @Property(key = DDK_CONNECTIONS_PATH, injector = DeploymentDescriptorConnectionListInjector.class, nullable = false)
    private List<ConnectionBean> connections;

    @Property(key = DDK_DEFAULT_CONNECTION_ID, nullable = false)
    private String defaultConnectionId;

    @Property(key = DDK_DEFAULT_CONNECTION_ID, injector = DeploymentDescriptorDefaultConnectionInjector.class, nullable = false)
    private ConnectionBean defaultConnection;

    @Property(key = DDK_DESCRIPTOR_CLASS)
    private Class<?> descriptorClass;

    @Property(key = DDK_DESCRIPTOR_CLASS, injector =  DeploymentDescriptorInstanceInjector.class)
    private Object descriptorInstance;

    /**
     * @return the providerBean
     */
    public ProviderBean getProviderBean() {
        return providerBean;
    }

    /**
     * @param providerBean the providerBean to set
     */
    public void setProviderBean(ProviderBean providerBean) {
        this.providerBean = providerBean;
    }

    /**
     * @return the connectionsPath
     */
    public String getConnectionsPath() {
        return connectionsPath;
    }

    /**
     * @param connectionsPath the connectionsPath to set
     */
    public void setConnectionsPath(String connectionsPath) {
        this.connectionsPath = connectionsPath;
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
     * @return the defaultConnectionId
     */
    public String getDefaultConnectionId() {
        return defaultConnectionId;
    }

    /**
     * @param defaultConnectionId the defaultConnectionId to set
     */
    public void setDefaultConnectionId(String defaultConnectionId) {
        this.defaultConnectionId = defaultConnectionId;
    }

    /**
     * @return the defaultConnection
     */
    public ConnectionBean getDefaultConnection() {
        return defaultConnection;
    }

    /**
     * @param defaultConnection the defaultConnection to set
     */
    public void setDefaultConnection(ConnectionBean defaultConnection) {
        this.defaultConnection = defaultConnection;
    }

    /**
     * @return the descriptorClass
     */
    public Class<?> getDescriptorClass() {
        return descriptorClass;
    }

    /**
     * @param descriptorClass the descriptorClass to set
     */
    public void setDescriptorClass(Class<?> descriptorClass) {
        this.descriptorClass = descriptorClass;
    }

    /**
     * @return the descriptorInstance
     */
    public Object getDescriptorInstance() {
        return descriptorInstance;
    }

    /**
     * @param descriptorInstance the descriptorInstance to set
     */
    public void setDescriptorInstance(Object descriptorInstance) {
        this.descriptorInstance = descriptorInstance;
    }


}
