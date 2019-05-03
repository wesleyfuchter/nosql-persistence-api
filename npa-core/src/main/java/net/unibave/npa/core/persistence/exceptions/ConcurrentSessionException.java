/**
 *
 */
package net.unibave.npa.core.persistence.exceptions;

/**
 * @author wesley
 */
public class ConcurrentSessionException extends PersistenceException {

    public static final String DEFAULT_ERR_MESSAGE = "";

    /**
     *
     */
    private static final long serialVersionUID = 7197255961058806150L;

    /**
     *
     */
    public ConcurrentSessionException() {
    }

    /**
     * @param message
     */
    public ConcurrentSessionException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public ConcurrentSessionException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public ConcurrentSessionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public ConcurrentSessionException(String message, Throwable cause, boolean enableSuppression,
                                      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
