/**
 *
 */
package net.unibave.npa.core.persistence.exceptions;

/**
 * It represents all exceptions API
 * context operations
 *
 * @author wesley
 */
public class ContextException extends PersistenceException {

    /**
     *
     */
    private static final long serialVersionUID = 8502258489931349696L;

    /**
     *
     */
    public ContextException() {
    }

    /**
     * @param message
     */
    public ContextException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public ContextException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public ContextException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public ContextException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
