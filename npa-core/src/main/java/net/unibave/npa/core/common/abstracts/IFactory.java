/**
 *
 */
package net.unibave.npa.core.common.abstracts;

/**
 *
 *
 * @author wesley
 */
public interface IFactory<E, O> {

    /**
     * Factory a object
     *
     * @param e object value to factory
     * @return the result object
     */
    O factory(final E e) throws Exception;

}
