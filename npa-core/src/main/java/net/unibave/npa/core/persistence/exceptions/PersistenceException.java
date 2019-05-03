/**
 *
 */
package net.unibave.npa.core.persistence.exceptions;

/**
 * It represents all exceptions api
 *
 * @author wesley
 */
public class PersistenceException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = -6121504354597149159L;

    /**
     *
     */
    public PersistenceException() {
    }

    /**
     * @param message
     */
    public PersistenceException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public PersistenceException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public PersistenceException(String message, Throwable cause, boolean enableSuppression,
                                boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
