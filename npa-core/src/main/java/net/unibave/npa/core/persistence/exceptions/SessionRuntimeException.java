/**
 *
 */
package net.unibave.npa.core.persistence.exceptions;

/**
 * It represents all exceptions API
 * session operations
 *
 * @author wesley
 */
public class SessionRuntimeException extends PersistenceRuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -245755441105496999L;

    /**
     *
     */
    public SessionRuntimeException() {
    }

    /**
     * @param message
     */
    public SessionRuntimeException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public SessionRuntimeException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public SessionRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public SessionRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
