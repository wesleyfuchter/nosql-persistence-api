package net.unibave.npa.core.persistence.model;

import net.unibave.npa.core.persistence.enumeration.OperationStatus;

import java.lang.reflect.Method;

@Deprecated
public class OperationBean {

    private Method methodToExecute;
    private OperationStatus operationStatus = OperationStatus.WAIT;
    private Object[] methodArgs;
    private Object instanceClass;
    private SessionBean session;

    public void execute() throws Exception {
        try {
            methodToExecute.invoke(instanceClass, methodArgs);
            operationStatus = OperationStatus.OK;
        } catch (Exception e) {
            operationStatus = OperationStatus.ERROR;
            throw e;
        }
    }

}
