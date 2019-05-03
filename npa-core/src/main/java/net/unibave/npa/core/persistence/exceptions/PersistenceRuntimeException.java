/**
 *
 */
package net.unibave.npa.core.persistence.exceptions;

/**
 * It represents all exceptions api
 *
 * @author wesley
 */
public class PersistenceRuntimeException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -6121504354597149159L;

    /**
     *
     */
    public PersistenceRuntimeException() {
    }

    /**
     * @param message
     */
    public PersistenceRuntimeException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public PersistenceRuntimeException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public PersistenceRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public PersistenceRuntimeException(String message, Throwable cause, boolean enableSuppression,
                                       boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
