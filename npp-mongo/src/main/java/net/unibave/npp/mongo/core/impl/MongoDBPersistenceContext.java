/**
 * 
 */
package net.unibave.npp.mongo.core.impl;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import net.unibave.npa.core.persistence.abstracts.AbstractPersistenceContext;

import java.util.Objects;

/**
 * @author wesley
 *
 */
public class MongoDBPersistenceContext extends AbstractPersistenceContext {

    protected MongoClient mongoClient;
    protected MongoDatabase dataBase;
    protected String base;
    protected Integer port;
    protected String host;

    public MongoDBPersistenceContext() {
    }

    public String getHost() {
        if (Objects.isNull(host)) {
            host = getContextBean().getDeploymentDescriptorBean().getDefaultConnection().getHost();
        }
        return host;
    }

    public String getBase() {
        if (Objects.isNull(base)) {
            base = getContextBean().getDeploymentDescriptorBean().getDefaultConnection().getBase();
        }
        return base;
    }

    public Integer getPort() {
        if (Objects.isNull(port)) {
            port = Integer.parseInt(getContextBean().getDeploymentDescriptorBean().getDefaultConnection().getPort());
        }
        return port;
    }

    public MongoClient getMongoClient() {
        if (Objects.isNull(mongoClient)) {
            mongoClient = new MongoClient(getHost(),getPort());
        }
        return mongoClient;
    }

    public MongoDatabase getDataBase() {
        if (Objects.isNull(dataBase)) {
            dataBase = getMongoClient().getDatabase(getBase());
        }
        return dataBase;
    }

    @Override
    protected void clearContext() throws Exception {
        this.mongoClient = null;
        this.dataBase = null;
        this.base = null;
        this.port = null;
        this.host = null;
    }
}
