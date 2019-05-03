package net.unibave.npa.core.persistence.model;

import net.unibave.npa.core.util.reflect.metainf.Property;
import java.io.Serializable;

/**
 * Represents a connection config file
 *
 * @author wesley
 * */
public class ConnectionBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static final String CONN_PROPERTY_ID = "id";
    @Property(key = CONN_PROPERTY_ID, nullable = false)
    private String id;

    public static final String CONN_PROPERTY_HOST = "host";
    @Property(key = CONN_PROPERTY_HOST, nullable = false)
    private String host;

    public static final String CONN_PROPERTY_PORT = "port";
    @Property(key = CONN_PROPERTY_PORT, nullable = false)
    private String port;

    public static final String CONN_PROPERTY_BASE = "base";
    @Property(key = CONN_PROPERTY_BASE, nullable = false)
    private String base;

    public ConnectionBean() {
    }

    public ConnectionBean(String id, String host, String port, String base) {
        this.id = id;
        this.host = host;
        this.port = port;
        this.base = base;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConnectionBean that = (ConnectionBean) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }
}
