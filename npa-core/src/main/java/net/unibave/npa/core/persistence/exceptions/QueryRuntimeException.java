/**
 *
 */
package net.unibave.npa.core.persistence.exceptions;

/**
 * It represents all exceptions API
 * query operations
 *
 * @author wesley
 */
public class QueryRuntimeException extends PersistenceRuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -2802259809211675148L;

    /**
     *
     */
    public QueryRuntimeException() {
    }

    /**
     * @param message
     */
    public QueryRuntimeException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public QueryRuntimeException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public QueryRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public QueryRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
