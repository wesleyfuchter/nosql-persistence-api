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
public class ControllerException extends PersistenceException {

    /**
     *
     */
    private static final long serialVersionUID = -2863870606957761911L;

    /**
     *
     */
    public ControllerException() {
    }

    /**
     * @param message
     */
    public ControllerException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public ControllerException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public ControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public ControllerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
