package net.unibave.npa.core.persistence.exceptions;

public class MissingPropertyException extends PersistenceException {

    /**
     *
     */
    private static final long serialVersionUID = 81858160445427878L;

    public MissingPropertyException() {
    }

    public MissingPropertyException(String message) {
        super(message);
    }

    public MissingPropertyException(Throwable cause) {
        super(cause);
    }

    public MissingPropertyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MissingPropertyException(String message, Throwable cause, boolean enableSuppression,
                                    boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
