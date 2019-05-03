/**
 *
 */
package net.unibave.npa.core.persistence.exceptions;

/**
 * It represents all exceptions API
 * control operations
 *
 * @author wesley
 */
public class ControllerRuntimeException extends PersistenceRuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -2863870606957761911L;

    /**
     *
     */
    public ControllerRuntimeException() {
    }

    /**
     * @param message
     */
    public ControllerRuntimeException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public ControllerRuntimeException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public ControllerRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public ControllerRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
