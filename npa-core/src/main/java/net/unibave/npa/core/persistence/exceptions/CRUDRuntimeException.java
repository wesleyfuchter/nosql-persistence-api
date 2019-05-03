/**
 *
 */
package net.unibave.npa.core.persistence.exceptions;

/**
 * It represents all exceptions API
 * CRUD operations
 *
 * @author wesley
 */
public class CRUDRuntimeException extends PersistenceRuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 5296107699911928818L;

    /**
     *
     */
    public CRUDRuntimeException() {
    }

    /**
     * @param message
     */
    public CRUDRuntimeException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public CRUDRuntimeException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public CRUDRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public CRUDRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
