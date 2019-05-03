/**
 *
 */
package net.unibave.npa.core.common.abstracts;

/**
 * Responsible for standardizing factories classes.
 * Classes factories has as its build objects that
 * have a high bureaucracy construction.
 * <p>
 * E - type parameter represents input factory object
 * O - type parameter represents output factory object
 *
 * @author wesley
 */
public interface IFactoryArgs<E, O> {

    /**
     * Factory a object
     *
     * @param e value to factory
     * @param args args to constructor
     * @return the result object
     */

    O factory(final E e, Object ... args) throws Exception;
    
}
