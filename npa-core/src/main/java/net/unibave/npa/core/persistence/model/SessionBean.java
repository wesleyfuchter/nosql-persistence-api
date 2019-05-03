package net.unibave.npa.core.persistence.model;

import net.unibave.npa.core.persistence.abstracts.AbstractPersistenceProvider;
import net.unibave.npa.core.persistence.abstracts.ICrud;
import net.unibave.npa.core.persistence.abstracts.IOpenable;
import net.unibave.npa.core.persistence.enumeration.SessionStatus;
import net.unibave.npa.core.persistence.exceptions.ConcurrentSessionException;
import net.unibave.npa.core.persistence.exceptions.NotOpenSessionException;
import net.unibave.npa.core.persistence.impl.crud.InitialCRUD;
import net.unibave.npa.core.persistence.impl.crud.SessionCRUDOld;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class SessionBean implements IOpenable, AutoCloseable, Serializable {

    protected SessionBean() {
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private ConnectionBean connection;
    private EntityBean entityBean;
    private SessionStatus sessionStatus;
    private List<OperationBean> operations;
    private ICrud<Object, Object> crud;
    private ContextBean contextBean;
    private Class<?> entityClass;
    private Class<?> keyClass;

    @Override
    public void close() throws Exception {
        try {
            getContextBean().getSessions().remove(this);
        } catch (Exception e) {
            sessionStatus = SessionStatus.ERROR;
            throw e;
        }
        sessionStatus = SessionStatus.CLOSED;
    }

    private void verifySession() throws Exception {
        if (!sessionStatus.equals(SessionStatus.OPEN)) {
            throw new NotOpenSessionException(NotOpenSessionException.DEFAULT_ERR_MESSAGE);
        }
    }

    @Override
    public void open() throws Exception {
        this.sessionStatus = SessionStatus.OPEN;
    }

    /**
     * @param e
     * @throws Exception
     * @see EntityBean
     */
    public Object create(Object e) throws Exception {
        verifySession();
        return getCrud().create(e);
    }

    /**
     * @param k
     * @throws Exception
     * @returnexceptionToThrow
     * @see EntityKeyBean
     */
    public Object read(Object k) throws Exception {
        verifySession();
        return getCrud().read(k);
    }

    /**
     * @return
     * @throws Exception
     * @see SessionBean
     */
    public List<Object> read() throws Exception {
        verifySession();
        return getCrud().read();
    }

    /**
     * @param e
     * @throws Exception
     * @see EntityBean
     */
    public Object update(Object e) throws Exception {
        verifySession();
        return getCrud().update(e);
    }

    /**
     * @param e
     * @throws Exception
     * @see EntityBean
     */
    public Object delete(Object e) throws Exception {
        verifySession();
        return getCrud().delete(e);
    }

    /**
     * @return the connection
     */
    public ConnectionBean getConnection() {
        return connection;
    }

    /**
     * @param connection the connection to set
     */
    public void setConnection(ConnectionBean connection) {
        this.connection = connection;
    }

    /**
     * @return the entityBean
     */
    public EntityBean getEntityBean() {
        return entityBean;
    }

    /**
     * @param entityBean the entityBean to set
     */
    public void setEntityBean(EntityBean entityBean) {
        this.entityBean = entityBean;
    }

    /**
     * @return the sessionStatus
     */
    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    /**
     * @param sessionStatus the sessionStatus to set
     */
    public void setSessionStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    /**
     * @return the operations
     */
    public List<OperationBean> getOperations() {
        return operations;
    }

    /**
     * @param operations the operations to set
     */
    public void setOperations(List<OperationBean> operations) {
        this.operations = operations;
    }

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * @return the crud
     */
    public ICrud<Object, Object> getCrud() {
        if (Objects.isNull(crud)) {
            crud = new InitialCRUD(this);
        }
        return crud;
    }

    /**
     * @param crud the crud to set
     */
    public void setCrud(ICrud<Object, Object> crud) {
        this.crud = crud;
    }

    /**
     * @return the contextBean
     */
    public ContextBean getContextBean() {
        return contextBean;
    }

    /**
     * @param contextBean the contextBean to set
     */
    public void setContextBean(ContextBean contextBean) {
        this.contextBean = contextBean;
    }

    /**
     * @return the entityClass
     */
    public final Class<?> getEntityClass() {
        return entityClass;
    }

    /**
     * @param entityClass the entityClass to set
     */
    public final void setEntityClass(Class<?> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * @return the keyClass
     */
    public final Class<?> getKeyClass() {
        return keyClass;
    }

    /**
     * @param keyClass the keyClass to set
     */
    public final void setKeyClass(Class<?> keyClass) {
        this.keyClass = keyClass;
    }

    public AbstractPersistenceProvider getAbstractPersistenceProvider() throws Exception {
        return this.getContextBean().getDeploymentDescriptorBean().getProviderBean().getAbstractPersistenceProvider();
    }

}
