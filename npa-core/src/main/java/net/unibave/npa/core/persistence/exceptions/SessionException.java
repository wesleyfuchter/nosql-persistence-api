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
public class SessionException extends PersistenceException {

    /**
     *
     */
    private static final long serialVersionUID = -245755441105496999L;

    /**
     *
     */
    public SessionException() {
    }

    /**
     * @param message
     */
    public SessionException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public SessionException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public SessionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public SessionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
