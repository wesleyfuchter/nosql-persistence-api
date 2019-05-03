/**
 *
 */
package net.unibave.npa.core.persistence.exceptions;

import net.unibave.npa.core.persistence.metainf.Attribute;

/**
 * @author wesley
 */
public class InvalidAttributePersistenceRuntimeException extends PersistenceRuntimeException {

    public static final String DEFAULT_ERR_MESSAGE = "Attribute must be annotated with " + Attribute.class.getName();

    /**
     *
     */
    private static final long serialVersionUID = 2352115751786171985L;

    /**
     *
     */
    public InvalidAttributePersistenceRuntimeException() {

    }

    /**
     * @param message
     */
    public InvalidAttributePersistenceRuntimeException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public InvalidAttributePersistenceRuntimeException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public InvalidAttributePersistenceRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public InvalidAttributePersistenceRuntimeException(String message, Throwable cause, boolean enableSuppression,
                                                       boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
