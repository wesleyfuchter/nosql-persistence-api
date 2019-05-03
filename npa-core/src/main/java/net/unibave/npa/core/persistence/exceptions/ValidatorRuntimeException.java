/**
 *
 */
package net.unibave.npa.core.persistence.exceptions;

/**
 * It represents all exceptions API
 * validation operations
 *
 * @author wesley
 */
public class ValidatorRuntimeException extends PersistenceRuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -7972664862007569065L;

    /**
     *
     */
    public ValidatorRuntimeException() {
    }

    /**
     * @param message
     */
    public ValidatorRuntimeException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public ValidatorRuntimeException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public ValidatorRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public ValidatorRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
