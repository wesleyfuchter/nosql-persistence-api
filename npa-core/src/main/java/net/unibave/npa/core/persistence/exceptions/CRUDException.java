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
public class CRUDException extends PersistenceException {

    /**
     *
     */
    private static final long serialVersionUID = 5296107699911928818L;

    /**
     *
     */
    public CRUDException() {
    }

    /**
     * @param message
     */
    public CRUDException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public CRUDException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public CRUDException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public CRUDException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
