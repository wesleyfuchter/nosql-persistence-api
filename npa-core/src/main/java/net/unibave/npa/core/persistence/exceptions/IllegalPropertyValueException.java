/**
 *
 */
package net.unibave.npa.core.persistence.exceptions;


/**
 * @author wesle
 */
public class IllegalPropertyValueException extends PersistenceException {

    /**
     *
     */
    private static final long serialVersionUID = 6163640989664486019L;

    /**
     *
     */
    public IllegalPropertyValueException() {
    }

    /**
     * @param message
     */
    public IllegalPropertyValueException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public IllegalPropertyValueException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public IllegalPropertyValueException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public IllegalPropertyValueException(String message, Throwable cause, boolean enableSuppression,
                                         boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
