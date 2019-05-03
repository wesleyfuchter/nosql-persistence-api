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
public class ContextRuntimeException extends PersistenceRuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 8502258489931349696L;

    /**
     *
     */
    public ContextRuntimeException() {
    }

    /**
     * @param message
     */
    public ContextRuntimeException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public ContextRuntimeException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public ContextRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public ContextRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
