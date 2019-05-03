package net.unibave.npa.core.persistence.exceptions;

public class NotOpenSessionException extends PersistenceException {

    public static final String DEFAULT_ERR_MESSAGE = "The session must be open!";

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public NotOpenSessionException() {
    }

    public NotOpenSessionException(String message) {
        super(message);
    }

    public NotOpenSessionException(Throwable cause) {
        super(cause);
    }

    public NotOpenSessionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotOpenSessionException(String message, Throwable cause, boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
