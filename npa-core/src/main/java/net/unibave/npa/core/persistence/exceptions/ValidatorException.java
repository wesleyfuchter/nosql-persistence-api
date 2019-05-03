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
public class ValidatorException extends PersistenceException {

    /**
     *
     */
    private static final long serialVersionUID = -7972664862007569065L;

    /**
     *
     */
    public ValidatorException() {
    }

    /**
     * @param message
     */
    public ValidatorException(String message) {
        super(message);
    }

}
