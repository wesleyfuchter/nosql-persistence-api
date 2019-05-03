/**
 *
 */
package net.unibave.npa.core.persistence.exceptions;

import net.unibave.npa.core.persistence.metainf.Entity;

/**
 * @author wesley
 */
public class InvalidEntityPersistenceRuntimeException extends PersistenceRuntimeException {

    public static final String DEFAULT_ERR_MESSAGE = "Entity must be annotated with " + Entity.class.getName();

    /**
     *
     */
    private static final long serialVersionUID = 2352115751786171985L;

    /**
     *
     */
    public InvalidEntityPersistenceRuntimeException() {

    }

    /**
     * @param message
     */
    public InvalidEntityPersistenceRuntimeException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public InvalidEntityPersistenceRuntimeException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public InvalidEntityPersistenceRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public InvalidEntityPersistenceRuntimeException(String message, Throwable cause, boolean enableSuppression,
                                                    boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
