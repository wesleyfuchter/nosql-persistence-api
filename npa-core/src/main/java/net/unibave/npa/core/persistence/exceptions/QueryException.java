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
public class QueryException extends PersistenceException {

    /**
     *
     */
    private static final long serialVersionUID = -2802259809211675148L;

    /**
     *
     */
    public QueryException() {
    }

    /**
     * @param message
     */
    public QueryException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public QueryException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public QueryException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public QueryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
