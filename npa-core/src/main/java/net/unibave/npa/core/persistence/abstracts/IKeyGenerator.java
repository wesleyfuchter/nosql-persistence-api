/**
 *
 */
package net.unibave.npa.core.persistence.abstracts;

import net.unibave.npa.core.persistence.model.SessionBean;

/**
 * Agreement to standardize key generate objects.
 *
 * @author wesley
 */
public interface IKeyGenerator<K, E> {

    /**
     * Generates the key
     *
     * @param E entity to generate
     * @return key generated
     * @throws Exception
     */
    K generate(final E e, final SessionBean sessionBean) throws Exception;

}
